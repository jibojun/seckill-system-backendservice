package com.seckill.backend.service.api;

import com.seckill.backend.common.entity.OrderInfo;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/4_2:26 AM
 */
public interface IOrderService {
    void submitOrder(long orderId);

    OrderInfo queryOrder(long orderId);
}
