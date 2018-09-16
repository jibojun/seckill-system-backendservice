package com.seckill.backend.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.seckill.backend.common.constants.RedisConstants;
import com.seckill.backend.common.lock.RedisPool;
import com.seckill.backend.dao.mapper.OrderDao;
import com.seckill.backend.dao.mapper.ProductDao;
import com.seckill.backend.service.api.IOrderService;
import com.seckill.backend.service.cache.CacheManager;
import com.seckill.backend.service.mq.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

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


    /**
     * check product number in redis, by decr, when there is no amount, means seckill is failed
     *
     * @param itemId
     * @param buyNumber
     * @return
     */
    @Override
    public boolean createOrder(String itemId, int buyNumber) {
        Jedis jedis = RedisPool.getConnResource();
        try {
            jedis.watch(RedisConstants.PRODUCT_KEY_PREFIX + itemId);
            int currentNumber = Integer.valueOf(jedis.get(RedisConstants.PRODUCT_KEY_PREFIX + itemId));

            long remainingNumber = jedis.decrBy(RedisConstants.PRODUCT_KEY_PREFIX + itemId, buyNumber);
            if (remainingNumber < 0) {
                //failed seckill,recover amount
                jedis.incrBy(RedisConstants.PRODUCT_KEY_PREFIX + itemId, buyNumber);
                return false;
            }
        } catch (Exception e) {

        } finally {

        }
        return true;
    }
}
