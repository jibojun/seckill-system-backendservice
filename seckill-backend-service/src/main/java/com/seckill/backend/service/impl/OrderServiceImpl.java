package com.seckill.backend.service.impl;

import com.seckill.backend.common.entity.OrderInfo;
import com.seckill.backend.dao.mapper.OrderDao;
import com.seckill.backend.service.api.IOrderService;
import com.seckill.backend.service.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/4_2:28 AM
 */
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private OrderDao orderDao;




    @Override
    public void submitOrder(long orderId) {

    }

    @Override
    public OrderInfo queryOrder(long orderId) {
        OrderInfo orderInfo=cacheManager.getOrderCache(orderId);
        //TODO, distribution lock, only 1 thread to get data from DB and update cache
        return orderInfo;
    }
}
