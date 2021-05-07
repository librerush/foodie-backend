package com.example.foodie.entity;

import javax.persistence.*;

@Entity
@Table(name = "product", schema = "foodie_scheme")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private double price;

    @JoinColumn(name = "category_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

    @JoinColumn(name = "brand_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Brand brand;

    @Column(length = 2048)
    private String description;

    public Product() {
    }

    public Product(String name, double price, Category category, Brand brand, String description) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.description = description;
    }

    public Product(long id, String name, double price, Category category, Brand brand, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", brand=" + brand +
                ", description='" + description + '\'' +
                '}';
    }
}
