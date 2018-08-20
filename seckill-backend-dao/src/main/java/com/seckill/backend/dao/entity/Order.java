package com.seckill.backend.dao.entity;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/18_5:47 PM
 */
public class Order {
    private int orderId;
    private int productId;
    private int productNumbers;
    private BigDecimal orderAmount;
    private boolean isSuccess;
    private Calendar orderDate;

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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductNumbers() {
        return productNumbers;
    }

    public void setProductNumbers(int productNumbers) {
        this.productNumbers = productNumbers;
    }
}
