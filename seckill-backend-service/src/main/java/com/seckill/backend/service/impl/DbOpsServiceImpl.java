package com.seckill.backend.service.impl;

import com.seckill.backend.common.logger.LogUtil;
import com.seckill.backend.dao.entity.Order;
import com.seckill.backend.dao.entity.Product;
import com.seckill.backend.dao.mapper.OrderDao;
import com.seckill.backend.dao.mapper.ProductDao;
import com.seckill.backend.service.api.IDbOpsService;
import com.seckill.backend.service.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Author: Bojun Ji
 * @Date: Created in 2018-09-19 11:17
 * @Description:
 */
@Component
public class DbOpsServiceImpl implements IDbOpsService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CacheManager cacheManager;

    /**
     * create order and update product amount
     *
     * @param order
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public boolean createOrderAndUpdateProduct(Order order) {
        if (order == null) {
            return false;
        }
        try {
            Product product = getCurrentProductInfo(order.getProductId());
            if (product == null) {
                return false;
            }
            //order amount
            order.setOrderAmount(product.getPrice().multiply(BigDecimal.valueOf(order.getProductNumbers())));
            product.setAmount(product.getAmount() - order.getProductNumbers());
            orderDao.insert(order);
            productDao.updateProductAmount(product);
            //invalid product cache
            cacheManager.removeProductCache(product.getProductId());
        } catch (Exception e) {
            LogUtil.logError(this.getClass(), String.format("met exception when doing order/product update, exception is: %s", e));
            return false;
        }
        return true;
    }

    private Product getCurrentProductInfo(int productId) {
        try {
            return productDao.queryProductByPk(productId);
        } catch (Exception e) {
            LogUtil.logError(this.getClass(), String.format("met exception when query product info by pk, exception is: %s", e));
        }
        return null;
    }
}
