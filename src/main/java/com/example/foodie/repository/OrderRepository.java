package com.example.foodie.repository;

import com.example.foodie.entity.Order;
import com.example.foodie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findOrderByDate(LocalDateTime date);

    List<Order> findOrderByUser(User user);
}
