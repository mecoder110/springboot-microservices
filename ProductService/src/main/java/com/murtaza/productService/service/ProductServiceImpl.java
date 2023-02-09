package com.murtaza.productService.service;

import com.murtaza.productService.entity.Product;
import com.murtaza.productService.exception.ProductNotFoundException;
import com.murtaza.productService.model.RequestProduct;
import com.murtaza.productService.model.ResponseProduct;
import com.murtaza.productService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseProduct getProduct(Long id) {
        log.info("===> object finding");
        Product product = productRepository.findById(id).
                orElseThrow(() -> new ProductNotFoundException("Product not found with given ID : "+id,"PRODUCT_NOT_FOUND"));
        ResponseProduct responseProduct = new ResponseProduct();
        copyProperties(product, responseProduct);
        log.info("===>RequestProduct has created..");
        return responseProduct;

    }

    public Long dumpProduct(RequestProduct requestProduct) {
        Product product = Product.builder()
                .productName(requestProduct.getProductName())
                .price(requestProduct.getPrice())
                .quantity(requestProduct.getQuantity())
                .build();
        log.info("Product has created");
        return productRepository.save(product).getId();
    }

    @Override
    public void reduceQuantity(Long id, Long quantity) {
        log.info("Product id {} to be reduce by {}",id,quantity);
        Product product = productRepository.findById(id).orElseThrow(()->
                new ProductNotFoundException("Product not found with id: "+id,"PRODUCT_NOT_FOUND"));
        if (product.getQuantity() < quantity){
            throw new ProductNotFoundException("Product not sufficient to be ordered", "OUT_OF_STOCK");
        }
        product.setQuantity(product.getQuantity()-quantity);
        productRepository.save(product);
        log.info("Quantity reduced successfully");
    }
}
