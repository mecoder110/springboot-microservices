package com.murtaza.orderService.controller;

import com.murtaza.orderService.model.RequestOrder;
import com.murtaza.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody RequestOrder requestOrder){
        Long orderId = orderService.placeOrder(requestOrder);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }
}
