package com.example.foodie.controller;

import com.example.foodie.entity.Order;
import com.example.foodie.entity.Product;
import com.example.foodie.entity.User;
import com.example.foodie.repository.OrderRepository;
import com.example.foodie.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    @Operation(summary = "Get an order by 'id'")
    Optional<Order> getById(@Parameter(description = "id of order")
                            @PathVariable Long id) {
        return orderRepository.findById(id);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Create new order for user")
    Order createOrder(@Parameter(description = "id of user")
                      @PathVariable Long id,
                      @Parameter(description = "a list of products")
                      @RequestBody List<Product> productList) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        Order order = new Order(LocalDate.now(), user);
        order.setProducts(productList);
        order = orderRepository.save(order);
        return order;
    }
}
