package com.seckill.backend.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.seckill.backend.common.constants.RedisConstants;
import com.seckill.backend.common.lock.RedisPool;
import com.seckill.backend.common.logger.LogUtil;
import com.seckill.backend.dao.mapper.OrderDao;
import com.seckill.backend.dao.mapper.ProductDao;
import com.seckill.backend.service.api.IOrderIdGenService;
import com.seckill.backend.service.api.IOrderService;
import com.seckill.backend.service.cache.CacheManager;
import com.seckill.backend.service.mq.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/4_2:28 AM
 */
@Service(timeout = 1500, retries = 3)
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private IOrderIdGenService orderIdGenService;


    /**
     * check product number in redis, by decr, when there is no amount, means seckill is failed
     *
     * @param itemId
     * @param buyNumber
     * @return
     */
    @Override
    public Long createOrder(String itemId, int buyNumber) {
        Jedis jedis = RedisPool.getConnResource();
        try {
            jedis.watch(RedisConstants.PRODUCT_KEY_PREFIX + itemId);
            int currentNumber = Integer.valueOf(jedis.get(RedisConstants.PRODUCT_KEY_PREFIX + itemId));
            if (currentNumber <= 0 || currentNumber - buyNumber < 0) {
                //no amount or not enough
                LogUtil.logInfo(this.getClass(), String.format("seckill failed since no amount of product,or the desired buy numbers is more than current amount, product: %s", itemId));
                return null;
            }
            //begin transaction
            Transaction transaction = jedis.multi();
            //decr
            transaction.decrBy(RedisConstants.PRODUCT_KEY_PREFIX + itemId, buyNumber);
            List<Object> result = transaction.exec();
            if (CollectionUtils.isEmpty(result)) {
                //failed seckill
                LogUtil.logInfo(this.getClass(), String.format("seckill failed since product key adjust by other threads, product: %s", itemId));
                return null;
            }
            LogUtil.logInfo(this.getClass(), String.format("seckill is successful, product: %s, begin to push message to MQ and create order", itemId));
            //TODO create order
            //get order id
            long orderId = orderIdGenService.getOrderId();
            return orderId;
        } catch (Exception e) {
            LogUtil.logError(this.getClass(), String.format("seckill failed due to exception, item is :%s, exception is: %s", itemId, e));
        } finally {
            jedis.unwatch();
        }
        return null;
    }
}
