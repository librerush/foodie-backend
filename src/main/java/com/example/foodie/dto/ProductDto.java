package com.example.foodie.dto;

import com.example.foodie.entity.Brand;
import com.example.foodie.entity.Category;

public class ProductDto {
    private String name;

    private double price;

    private Category category;

    private Brand brand;

    private String description;

    private String image;

    public ProductDto() {
    }

    public ProductDto(String name, double price, Category category, Brand brand, String description, String image) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
