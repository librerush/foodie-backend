package com.example.foodie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 2048)
    private String name;

    private double price;

    @JoinColumn(name = "category_id")
    @ManyToOne()
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Category category;

    @JoinColumn(name = "brand_id")
    @ManyToOne()
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Brand brand;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Set<OrderProduct> orderProduct = new HashSet<>();

    @Column(length = 32768)
    private String description;

    @Column(length = 4096)
    private String image;

    public Product() {
    }

    public Product(String name, double price, Category category,
                   Brand brand, String description, String image) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.description = description;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<OrderProduct> getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(Set<OrderProduct> orderProduct) {
        this.orderProduct = orderProduct;
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
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0 && Objects.equals(name, product.name) && Objects.equals(category, product.category) && Objects.equals(brand, product.brand) && Objects.equals(description, product.description) && Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category, brand, description, image);
    }
}
