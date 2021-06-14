package com.example.foodie.repository;

import com.example.foodie.entity.OrderProduct;
import com.example.foodie.entity.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {

    @Query("select op from OrderProduct op \n" +
            "    join Order o on op.order = o join User u on o.user = u.id\n" +
            "    where u.id = :id")
    List<OrderProduct> findOrderProductByUserId(Long id);
}
