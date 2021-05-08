package com.example.foodie.repository;

import com.example.foodie.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>,
        org.springframework.data.repository.Repository<Order, Long> {
    
    List<Order> findOrderByDate(LocalDate date);
}
