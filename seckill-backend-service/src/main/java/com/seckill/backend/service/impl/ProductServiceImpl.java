package com.seckill.backend.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.seckill.backend.common.lock.RedisDistributionLock;
import com.seckill.backend.common.logger.LogUtil;
import com.seckill.backend.dao.entity.Product;
import com.seckill.backend.dao.mapper.ProductDao;
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

    @Autowired
    private ProductDao productDao;

    private RedisDistributionLock lock = new RedisDistributionLock("getProductInfo", 15L);

    @Override
    public ProductInfo queryProductInfo(long productId) {
        ProductInfo cachedProductInfo = cacheManager.getProductCache(productId);
        if (cachedProductInfo == null) {
            //no cache, using distribution lock to let only 1 thread to query DB and update cache
            if (lock.tryLock()) {
                //TODO:got lock, query DB and update cache, unlock finally when operation finished
                Product dbResult = productDao.queryProductByPk(productId);
                ProductInfo tmpProductInfo=new ProductInfo(dbResult.getProductId(),dbResult.getProductName(),dbResult.getPrice(),dbResult.getAmount());
                cacheManager.putProductCache(tmpProductInfo);
                lock.unlock();
            } else {
                //no lock, wait, try read cache again when it's updated
                Long timeOutMillis = System.currentTimeMillis() + 3;
                while (lock.isLocked()) {
                    //wait 5s, otherwise, return null for timeout
                    if (System.currentTimeMillis() >= timeOutMillis) {
                        LogUtil.logInfo(this.getClass(), String.format("product: %s, waiting for update cache time out", productId));
                        return null;
                    }
                }
            }
            return cacheManager.getProductCache(productId);
        } else {
            //cache existed, return directly
            LogUtil.logInfo(this.getClass(), String.format("product: %s, hit cache, data is: %s", productId, cachedProductInfo));
            return cachedProductInfo;
        }
    }

    @Override
    public List<ProductInfo> queryProductInfo(List<Long> productIds) {
        return null;
    }
}
