package com.example.foodie.dto;

import com.example.foodie.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderDto {
    @JsonProperty("user_id")
    private Long userId;

    private List<Product> products;

    public OrderDto() {
    }

    public OrderDto(Long userId, List<Product> products) {
        this.userId = userId;
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
