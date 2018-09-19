package com.seckill.backend.dao.mapper;

import com.seckill.backend.dao.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @Author: Bojun Ji
 * @Description:
 * @Date: 2018/8/18_5:57 PM
 */
public interface ProductDao {
    List<Product> queryAllProducts() throws DataAccessException;

    Product queryProductByPk(@Param("productId") int productId) throws DataAccessException;

    List<Product> queryProductByName(@Param("productName") String productName) throws DataAccessException;

    int updateProductAmount(Product product) throws DataAccessException;
}
