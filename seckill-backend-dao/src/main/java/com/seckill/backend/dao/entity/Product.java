package com.seckill.backend.dao.entity;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/18_5:47 PM
 */
public class Product {
    private int productId;

    private String productName;

    private BigDecimal price;

    private int amount;

    private Calendar updateTime;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }
}
