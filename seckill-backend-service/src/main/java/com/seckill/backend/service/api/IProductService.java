package com.seckill.backend.service.api;

import java.util.List;

/**
 * @Author: Bojun Ji
 * @Description: product interface
 * @Date: 2018/8/4_1:43 AM
 */
public interface IProductService {
    ProductInfo queryProductInfo(long productId);

    List<ProductInfo> queryProductInfo(List<Long> productIds);
}
