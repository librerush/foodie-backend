package com.example.foodie.controller;

import com.example.foodie.dto.OrderDto;
import com.example.foodie.dto.ResultDto;
import com.example.foodie.entity.Order;
import com.example.foodie.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an order by 'id'")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Order not found")
    Order getById(@Parameter(description = "id of order")
                  @PathVariable Long id) {
        return orderService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Create new order for user")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "User not found")
    Order createOrder(@Parameter(description = "user id and a list of products")
                      @RequestBody OrderDto orderDto) {
        return orderService.create(orderDto);
    }

    @DeleteMapping
    @Operation(summary = "Delete an order")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Order not found")
    ResultDto deleteOrder(@Parameter(description = "an order")
                          @RequestBody Order order) {
        return orderService.delete(order);
    }

    @PutMapping
    @Operation(summary = "Update an order")
    Order updateOrder(@Parameter(description = "an order")
                      @RequestBody Order order) {
        return orderService.update(order);
    }
}
