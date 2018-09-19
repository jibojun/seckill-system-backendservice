package com.seckill.backend.service.impl;

import com.seckill.backend.dao.entity.Order;
import com.seckill.backend.dao.mapper.OrderDao;
import com.seckill.backend.dao.mapper.ProductDao;
import com.seckill.backend.service.api.IDbOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    @Transactional
    public boolean createOrderAndUpdateProduct(Order order) {
        //order amount
        order.setOrderAmount(getOrderAmount(order.getProductId(), order.getProductNumbers()));
        return false;
    }

    /**
     * calculate the order amount
     *
     * @param productId
     * @param productNumbers
     * @return
     */
    private BigDecimal getOrderAmount(int productId, int productNumbers) {
        return null;
    }

}
