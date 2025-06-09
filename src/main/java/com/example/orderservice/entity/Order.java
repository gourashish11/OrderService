package com.example.orderservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private Long id;
    private Long customerId;
    private Long productId;

    public Order(Long id, Long customerId, Long productId) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
    }
}
