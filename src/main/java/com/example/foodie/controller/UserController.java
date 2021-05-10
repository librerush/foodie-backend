package com.example.foodie.controller;

import com.example.foodie.entity.Order;
import com.example.foodie.entity.User;
import com.example.foodie.repository.OrderRepository;
import com.example.foodie.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders/{id}")
    @Operation(summary = "Get all orders of user by 'id'")
    List<Order> getUserOrders(@Parameter(description = "user id")
                              @PathVariable Long id) {
        List<Order> orders = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return orderRepository
                    .findOrderByUser(user.get())
                    /*.stream()
                    .peek(order ->
                            order.getProducts().forEach(product -> product.setOrders(null))).collect(Collectors.toList())*/;
        }
        return orders;
    }

    @GetMapping("/orders")
    @Operation(summary = "Get all orders of user by 'email'")
    List<Order> getUserOrdersByEmail(@Parameter(description = "user email")
                                     @RequestParam("email") String email) {
        List<Order> orders = new ArrayList<>();
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            return orderRepository.findOrderByUser(user.get());
        }
        return orders;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by 'id'")
    Optional<User> getById(@Parameter(description = "id of user")
                           @PathVariable("id") Long id) {
        return userRepository.findById(id);
    }

    @GetMapping
    @Operation(summary = "Get a user by 'email'")
    Optional<User> getByEmailAndPassword(@Parameter(description = "email of user")
                                         @RequestParam("email") String email) {
        return userRepository.findUserByEmail(email);
    }

    @PostMapping
    @Operation(summary = "Create a new user (email must be unique)")
    User create(@Parameter(description = "name of user")
                @RequestParam("name") String name,
                @Parameter(description = "email of user")
                @RequestParam("email") String email,
                @Parameter(description = "password of user")
                @RequestParam("password") String password) {
        User user = new User(name, email, password);
        user = userRepository.save(user);
        return user;
    }
}
