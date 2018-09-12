package com.seckill.backend.service.api;


/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/4_2:26 AM
 */
public interface IOrderService {
    boolean createOrder(String itemId, int buyNumber);
}
