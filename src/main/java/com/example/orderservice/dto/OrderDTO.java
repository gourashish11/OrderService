package com.example.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {
    @NotNull(message = "id can not be null")
    private Long id;
    private Long customerId;
    private Long orderId;
}
