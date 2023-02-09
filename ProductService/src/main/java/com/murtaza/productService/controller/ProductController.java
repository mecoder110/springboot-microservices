package com.murtaza.productService.controller;

import com.murtaza.productService.entity.Product;
import com.murtaza.productService.model.RequestProduct;
import com.murtaza.productService.model.ResponseProduct;
import com.murtaza.productService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("{id}")
    public ResponseEntity<ResponseProduct> getProduct(
            @PathVariable Long id){
        return new ResponseEntity(productService.getProduct(id),HttpStatus.FOUND );
    }
    @PostMapping("add")
    public ResponseEntity<Long> addProduct(
            @RequestBody RequestProduct requestProduct){
        return new ResponseEntity(productService.dumpProduct(requestProduct),HttpStatus.CREATED);
    }
    @PutMapping("reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable("id") Long id , @RequestParam Long quantity){
        productService.reduceQuantity(id, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


