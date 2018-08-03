package com.seckill.backend.common.entity;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/4_2:29 AM
 */
public class OrderInfo {
    private long orderId;

    private String orderStatus;

    private boolean seckillResult;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isSeckillResult() {
        return seckillResult;
    }

    public void setSeckillResult(boolean seckillResult) {
        this.seckillResult = seckillResult;
    }
}
