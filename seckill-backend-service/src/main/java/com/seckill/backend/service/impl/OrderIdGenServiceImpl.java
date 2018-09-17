package com.seckill.backend.service.impl;

import com.seckill.backend.common.constants.RedisConstants;
import com.seckill.backend.common.lock.RedisPool;
import com.seckill.backend.common.logger.LogUtil;
import com.seckill.backend.service.api.IOrderIdGenService;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/7/17_12:57 AM
 */
@Component
public class OrderIdGenServiceImpl implements IOrderIdGenService {
    private static final String ORDER_TABLE_NAME = "";

    @Override
    public long getOrderId() {
        try {
            Jedis jedis = RedisPool.getPool().getResource();
            return jedis.incr(String.format("%s%s", RedisConstants.SEQUENCE_KEY_PREFIX, ORDER_TABLE_NAME));
        } catch (Exception e) {
            LogUtil.logError(this.getClass(), String.format("get order id failed, exception: %s", e));
        }
        return 0;
    }
}
