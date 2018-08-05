package com.seckill.backend.service.cache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.seckill.backend.common.entity.OrderInfo;
import com.seckill.backend.common.entity.ProductInfo;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/6_1:15 AM
 */
@Component
public class CacheHolder {

    @CreateCache(name = "OrderCache", cacheType = CacheType.BOTH, localLimit = 1000, expire = 24, timeUnit = TimeUnit.HOURS)
    private Cache<Long, OrderInfo> orderCache;

    @CreateCache(name = "ProductCache", cacheType = CacheType.BOTH, localLimit = 1000, expire = 24, timeUnit = TimeUnit.HOURS)
    private Cache<Long, ProductInfo> productCache;

    public OrderInfo getOrderCache(long orderId) {
        return null;
    }

    public ProductInfo getProductCache(long productId) {
        return null;
    }

    public void removeOrderCache(long orderId) {

    }

    public void removeProductCache(long productId) {

    }

    public void setOrderCache(OrderInfo orderInfo) {

    }

    public void setProductCache(ProductInfo productInfo) {

    }
}
