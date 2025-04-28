package com.example.orderservice.service;

import com.example.orderservice.client.ProductClient;
import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.dto.ProductDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private ProductClient productClient;

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = repository.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(getOrderDTOFromOrder(order));
        }
        return orderDTOs;
    }

    public OrderDTO getOrderById(Long id) {
        Optional<Order> order = repository.findById(id);
        if (order.isPresent()) {
            return getOrderDTOFromOrder(order.get());
        }
        return null;
    }

    public ProductDTO getProduct(ProductDTO productDTO) {
        return productClient.getProductNew(productDTO);
    }

    private OrderDTO getOrderDTOFromOrder(Order order) {
        return new OrderDTO(order.getId(), order.getCustomerId(), order.getProductId());
    }
}
