package com.seckill.backend.service.impl;

import com.seckill.backend.dao.entity.Order;
import com.seckill.backend.service.api.IDbOpsService;
import org.springframework.stereotype.Component;

/**
 * @Author: Bojun Ji
 * @Date: Created in 2018-09-19 11:17
 * @Description:
 */
@Component
public class DbOpsServiceImpl implements IDbOpsService {
    /**
     * create order and update product amount
     *
     * @param order
     * @return
     */
    public boolean createOrderAndUpdateProduct(Order order) {
        return false;
    }
}
