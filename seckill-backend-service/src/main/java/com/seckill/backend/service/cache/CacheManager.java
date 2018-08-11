package com.seckill.backend.service.cache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.seckill.backend.common.entity.OrderInfo;
import com.seckill.backend.service.api.ProductInfo;
import com.seckill.backend.common.logger.LogUtil;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/6_1:15 AM
 */
@Component
public class CacheManager {

    @CreateCache(name = "OrderCache", cacheType = CacheType.BOTH, localLimit = 1000, expire = 24, timeUnit = TimeUnit.HOURS)
    private Cache<Long, OrderInfo> orderCache;

    @CreateCache(name = "ProductCache", cacheType = CacheType.BOTH, localLimit = 1000, expire = 24, timeUnit = TimeUnit.HOURS)
    private Cache<Long, ProductInfo> productCache;

    public OrderInfo getOrderCache(long orderId) {
        OrderInfo orderInfo = orderCache.get(orderId);
        if (orderInfo == null) {
            LogUtil.logWarn(CacheManager.class, "order cache not found or exception happened");
        }
        return orderCache.get(orderId);
    }

    public ProductInfo getProductCache(long productId) {
        ProductInfo productInfo = productCache.get(productId);
        if (productInfo == null) {
            LogUtil.logWarn(CacheManager.class, "product cache not found or exception happened");
        }
        return productCache.get(productId);
    }

    public boolean removeOrderCache(long orderId) {
        boolean result = orderCache.remove(orderId);
        if (!result) {
            LogUtil.logWarn(CacheManager.class, "order cache remove failed");
        }
        return result;
    }

    public boolean removeProductCache(long productId) {
        boolean result = productCache.remove(productId);
        if (!result) {
            LogUtil.logWarn(CacheManager.class, "product cache remove failed");
        }
        return result;
    }

    public void putOrderCache(OrderInfo orderInfo) {
        LogUtil.logInfo(CacheManager.class, String.format("put order cache: %s" + orderInfo));
        orderCache.put(orderInfo.getOrderId(), orderInfo);
    }

    public void putProductCache(ProductInfo productInfo) {
        LogUtil.logInfo(CacheManager.class, String.format("put product cache: %s" + productInfo));
        productCache.put(productInfo.getProductId(), productInfo);
    }
}
