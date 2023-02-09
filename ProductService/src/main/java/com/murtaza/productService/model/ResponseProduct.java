package com.murtaza.productService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseProduct {

    private String productName;
    private Long id;
    private double price;
    private  long quantity;

}
