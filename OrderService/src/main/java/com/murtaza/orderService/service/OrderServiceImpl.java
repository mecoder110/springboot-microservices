package com.murtaza.orderService.service;

import com.murtaza.orderService.entity.Order;
import com.murtaza.orderService.exception.CustomException;
import com.murtaza.orderService.external.client.ProductClient;
import com.murtaza.orderService.model.RequestOrder;
import com.murtaza.orderService.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public Long placeOrder(RequestOrder requestOrder) {
        //  orderEntity -> save the data with StaatusOrder created;
        //Product Service - Block Products(Reduce the quantity)
        // Payment Service -> Payments -Success -COMPLETE, else Cancle
        if (requestOrder.getQuantity()==0){
            throw new CustomException("Please enter quantity greater than ZERO", "ZERO_QUANTITY_NOT_ACCEPTED" , 403);
        }
        log.info("placing Order RequestOrder {}", requestOrder);

        productClient.reduceQuantity(requestOrder.getProductId(), requestOrder.getQuantity());
        log.info("Feign Client -- PRODUCT-SERVICE calling");
        log.info("PRODUCT ID: {} with quantity {}",requestOrder.getProductId(),requestOrder.getQuantity());
        Order order = Order.builder()
                .productId(requestOrder.getProductId())
                .orderDate(Instant.now())
                .orderStatus("CREATED")
                .quantity(requestOrder.getQuantity())
                .totalAmount(requestOrder.getTotalAmount())
                .build();

        Long id = orderRepository.save(order).getId();
        log.info("order has been placed with Order-id{}",id);
        return id;
    }

    @Override
    public Long cancleOrder(Long id) {
        return null;
    }
}
