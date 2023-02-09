package com.murtaza.orderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestOrder {



    private String orderName;
    private Long productId;
    private Long quantity;
    private Double totalAmount;
    private PaymentMode paymentMode;
}
