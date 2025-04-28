package com.example.orderservice.config;

import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
    @Bean
    public CommandLineRunner init(OrderRepository orderRepository) {
        return args -> {
            orderRepository.deleteAll();
            Order order1 = new Order(3L,1L);
            Order order2 = new Order(4L,2L);
            orderRepository.save(order1);
            orderRepository.save(order2);

        };
    }
}
