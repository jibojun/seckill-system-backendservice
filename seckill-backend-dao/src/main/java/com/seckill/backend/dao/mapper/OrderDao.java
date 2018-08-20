package com.seckill.backend.dao.mapper;

import com.seckill.backend.dao.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/18_5:57 PM
 */
public interface OrderDao {
    List<Order> queryByPk(@Param("orderId") int orderId);

    int insert(Order order);
}
