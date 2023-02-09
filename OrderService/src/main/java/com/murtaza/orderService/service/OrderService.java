package com.murtaza.orderService.service;

import com.murtaza.orderService.model.RequestOrder;

public interface OrderService {

    Long placeOrder(RequestOrder requestOrder);
    Long cancleOrder(Long id);
}


