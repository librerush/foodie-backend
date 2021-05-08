package com.example.foodie.controller;

import com.example.foodie.entity.Order;
import com.example.foodie.repository.OrderRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{id}")
    @Operation(summary = "Get order by 'id'")
    Optional<Order> getById(@Parameter(description = "id of order")
                            @PathVariable Long id) {
        return orderRepository.findById(id);
    }


}
