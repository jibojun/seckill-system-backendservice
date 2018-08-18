package com.seckill.backend.dao.entity;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/18_5:47 PM
 */
public class Order {
    private long orderId;
    private long productId;
    private long productNumbers;
    private BigDecimal orderAmount;
    private boolean isSuccess;
    private Calendar orderDate;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getProductNumbers() {
        return productNumbers;
    }

    public void setProductNumbers(long productNumbers) {
        this.productNumbers = productNumbers;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
