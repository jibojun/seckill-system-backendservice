package com.seckill.backend.service.impl;

import com.seckill.backend.common.logger.LogUtil;
import com.seckill.backend.dao.entity.Order;
import com.seckill.backend.dao.entity.Product;
import com.seckill.backend.dao.mapper.OrderDao;
import com.seckill.backend.dao.mapper.ProductDao;
import com.seckill.backend.service.api.IDbOpsService;
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
            order.setOrderAmount(getOrderTotalPrice(order.getProductId(), order.getProductNumbers(), product.getPrice()));
            product.setAmount(getProductAmount(order.getProductId(), order.getProductNumbers(), product.getAmount()));
            orderDao.insert(order);
            productDao.updateProductAmount(product);
        } catch (Exception e) {
            LogUtil.logError(this.getClass(), String.format("met exception when doing order/product update, exception is: %s", e));
            return false;
        }
        return true;
    }

    /**
     * calculate the order amount
     *
     * @param productId
     * @param productNumbers
     * @return
     */
    private BigDecimal getOrderTotalPrice(int productId, int productNumbers, BigDecimal productPrice) {
        return null;
    }

    /**
     * get product amount after order
     *
     * @param productId
     * @param productNumbers
     * @return
     */
    private int getProductAmount(int productId, int productNumbers, int currentNumber) {
        return 0;
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
