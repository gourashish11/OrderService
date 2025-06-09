package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.dto.ProductDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.error.ProductNotFoundException;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Value("${message: Default message}")
    private String message;

    @GetMapping("/message")
    public String getMessage() {
        return message;
    }

    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/getOrder")
    public ResponseEntity<?> getOrder(@Valid @RequestBody OrderDTO orderDTO) {
        OrderDTO orderResponse = orderService.getOrderById(orderDTO.getId());
        if (orderResponse == null) {
            throw new ProductNotFoundException("Product not found on" + orderDTO.getId());
        }
        return ResponseEntity.ok(orderResponse);

    }

    @PostMapping("/getProduct")
    public ResponseEntity<?> getProduct(@RequestBody ProductDTO productDTO) {
        try {
            ProductDTO product = orderService.getProduct(productDTO);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
