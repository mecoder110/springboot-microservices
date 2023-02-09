package com.murtaza.productService.service;

import com.murtaza.productService.entity.Product;
import com.murtaza.productService.model.RequestProduct;
import com.murtaza.productService.model.ResponseProduct;

public interface ProductService {
    ResponseProduct getProduct(Long id);

    Long dumpProduct(RequestProduct requestProduct);

    void reduceQuantity(Long id, Long quantity);
}

