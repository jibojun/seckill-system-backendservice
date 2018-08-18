package com.seckill.backend.service.api;

import java.math.BigDecimal;

/**
 * @Author: Bojun Ji
 * @Description: product info
 * @Date: 2018/8/4_1:44 AM
 */
public class ProductInfo {
    private long productId;

    private String productName;

    private BigDecimal productPrice;

    private int productAmount;

    public ProductInfo(long productId, String productName, BigDecimal productPrice, int productAmount) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
    }


    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }
}
