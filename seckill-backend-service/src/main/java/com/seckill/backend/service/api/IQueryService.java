package com.seckill.backend.service.api;

import com.seckill.backend.common.entity.ProductInfo;

import java.util.List;

/**
 * @Author: Bojun Ji
 * @Description: query interface
 * @Date: 2018/8/4_1:43 AM
 */
public interface IQueryService {
    ProductInfo queryProductInfo(String productName);

    List<ProductInfo> queryProductInfo(List<String> productNames);
}
