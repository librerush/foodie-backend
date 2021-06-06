package com.example.foodie.controller;

import com.example.foodie.dto.UserDto;
import com.example.foodie.entity.Order;
import com.example.foodie.entity.User;
import com.example.foodie.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/exists")
    @Operation(summary = "Check if user exists by email and password")
    boolean userExists(@RequestParam("email") String email,
                       @RequestParam("password") String password) {
        return userService.exists(email, password);
    }

    @GetMapping("/orders/{id}")
    @Operation(summary = "Get all orders of user by 'id'")
    List<Order> getUserOrders(@Parameter(description = "user id")
                              @PathVariable Long id) {
        return userService.getOrdersById(id);
    }

    @GetMapping("/orders")
    @Operation(summary = "Get all orders of user by 'email'")
    List<Order> getUserOrdersByEmail(@Parameter(description = "user email")
                                     @RequestParam("email") String email) {
        return userService.getOrdersByEmail(email);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by 'id'")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "User not found")
    User getById(@Parameter(description = "id of user")
                 @PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping
    @Operation(summary = "Get a user by 'email'")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "User not found")
    User getByEmail(@Parameter(description = "email of user")
                    @RequestParam("email") String email) {
        return userService.getByEmail(email);
    }

    @PostMapping
    @Operation(summary = "Create a new user (email must be unique)")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "User with email already exists")
    User createUser(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @PutMapping
    @Operation(summary = "Update a user")
    User updateUser(@RequestBody User user) {
        return userService.update(user);
    }
}
