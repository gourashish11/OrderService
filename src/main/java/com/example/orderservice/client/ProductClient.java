package com.example.orderservice.client;

import com.example.orderservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="product-service", url = "http://product-service:8081")
public interface ProductClient {
    @PostMapping("/api/getProduct")
    ProductDTO getProductNew(@RequestBody ProductDTO productDTO);
}
