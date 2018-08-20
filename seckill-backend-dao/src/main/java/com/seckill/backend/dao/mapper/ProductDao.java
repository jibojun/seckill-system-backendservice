package com.seckill.backend.dao.mapper;

import com.seckill.backend.dao.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/18_5:57 PM
 */
public interface ProductDao {
    List<Product> queryProductByPk(@Param("productId") long productId);

    List<Product> queryProductByName(String productName);

    int updateProduct(Product product);
}
