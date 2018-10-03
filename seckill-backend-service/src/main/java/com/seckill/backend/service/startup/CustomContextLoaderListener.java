package com.seckill.backend.service.startup;

import com.alibaba.fastjson.JSON;
import com.seckill.backend.common.constants.RedisConstants;
import com.seckill.backend.common.lock.RedisPool;
import com.seckill.backend.common.logger.LogUtil;
import com.seckill.backend.dao.entity.Product;
import com.seckill.backend.dao.entity.Sequence;
import com.seckill.backend.dao.mapper.ProductDao;
import com.seckill.backend.dao.mapper.sequence.SequenceDao;
import com.seckill.backend.service.api.ProductInfo;
import com.seckill.backend.service.cache.CacheManager;
import com.seckill.backend.service.mq.OrderConsumer;
import com.seckill.backend.service.mq.OrderMqConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletContextEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Bojun Ji
 * @Description: load cache when start up
 * @Date: 2018/8/4_2:24 AM
 */
public class CustomContextLoaderListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        //run spring context loader listener's initialization function
        super.contextInitialized(event);
        // load product from DB, store amount cache to redis
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        ProductDao productDao = context.getBean("productDao", ProductDao.class);
        List<Product> productList = productDao.queryAllProducts();
        if (!CollectionUtils.isEmpty(productList)) {
            Jedis jedis = RedisPool.getConnResource();
            for (Product product : productList) {
                //put amount to redis
                long returnedValue = jedis.incrBy(RedisConstants.PRODUCT_KEY_PREFIX + String.valueOf(product.getProductId()), product.getAmount());
                LogUtil.logInfo(this.getClass(), String.format("put product: [%s] to redis cache, amount is: %s", JSON.toJSONString(product), returnedValue));
                //init jetcache detailed date with caffeine/redis
                try {
                    CacheManager cacheManager = context.getBean("cacheManager", CacheManager.class);
                    cacheManager.putProductCache(new ProductInfo(product.getProductId(), product.getProductName(), product.getPrice(), product.getAmount()));
                } catch (Exception e) {
                    LogUtil.logError(this.getClass(), String.format("met excepetion when init jetcache detailed data, exception is: %s", e));
                }
            }
        }
        //load sequence info from DB and put it to redis
        SequenceDao sequenceDao = context.getBean("sequenceDao", SequenceDao.class);
        List<Sequence> sequenceList = sequenceDao.selectAllSequence();
        if (!CollectionUtils.isEmpty(sequenceList)) {
            Jedis jedis = RedisPool.getConnResource();
            for (Sequence sequence : sequenceList) {
                //put sequence to cache
                jedis.set(String.format("%s%s", RedisConstants.SEQUENCE_KEY_PREFIX, sequence.getTabeleName()), String.valueOf(sequence.getSequenceId()));
                LogUtil.logInfo(this.getClass(), String.format("put sequence: [%s] to redis cache", JSON.toJSONString(sequence)));
            }
        }
        //start kafka consumer
        OrderConsumer orderConsumer = context.getBean("OrderConsumer", OrderConsumer.class);
        List<String> topicList = new ArrayList<>();
        topicList.add(OrderMqConstants.ORDER_TOPIC_NAME);
        orderConsumer.consumer(topicList);
        LogUtil.logInfo(this.getClass(), String.format("start to consume topics: %s", JSON.toJSONString(topicList)));
    }
}
