package com.example.foodie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    @JsonProperty("user_id")
    private Long userId;
    /*
    * {
    *   "user_id": 1,
    *   "products": [
    *     {
    *       "product": {..}
    *       "quantity": 1
    *     }
    *   ]
    * }
    * */
    private List<ProductQuantityDto> products;

    public OrderDto() {
        products = new ArrayList<>();
    }

    public OrderDto(Long userId, List<ProductQuantityDto> products) {
        this.userId = userId;
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ProductQuantityDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductQuantityDto> products) {
        this.products = products;
    }
}
