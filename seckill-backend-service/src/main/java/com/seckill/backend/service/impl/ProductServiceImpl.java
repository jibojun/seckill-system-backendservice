package com.seckill.backend.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.seckill.backend.service.api.ProductInfo;
import com.seckill.backend.service.api.IProductService;
import com.seckill.backend.service.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Bojun Ji
 * @Description: query product info
 * @Date: 2018/8/4_1:48 AM
 */
@Service(timeout = 1500, retries = 3)
public class ProductServiceImpl implements IProductService {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public ProductInfo queryProductInfo(long productId) {
        return null;
    }

    @Override
    public List<ProductInfo> queryProductInfo(List<Long> productIds) {
        return null;
    }
}
