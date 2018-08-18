package com.seckill.backend.dao.entity;

import java.math.BigDecimal;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/18_5:47 PM
 */
public class Product {
    private long productId;

    private String productName;

    private BigDecimal price;

    private long amount;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
