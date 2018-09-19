package com.seckill.backend.service.api;

import com.seckill.backend.dao.entity.Order;

public interface IDbOpsService {
    boolean createOrderAndUpdateProduct(Order order);
}
