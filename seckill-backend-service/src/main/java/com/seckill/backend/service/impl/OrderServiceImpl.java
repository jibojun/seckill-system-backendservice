package com.seckill.backend.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.seckill.backend.dao.mapper.OrderDao;
import com.seckill.backend.dao.mapper.ProductDao;
import com.seckill.backend.service.api.IOrderService;
import com.seckill.backend.service.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/4_2:28 AM
 */
@Service(timeout = 1500, retries = 3)
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;


    @Override
    public boolean orderCheck(String itemId, int buyNumber) {
        return false;
    }
}
