package com.example.foodie.repository;

import com.example.foodie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,
        org.springframework.data.repository.Repository<User, Long> {

    List<User> findUserByName(String name);

    List<User> findUserByEmail(String email);

    List<User> findUserByEmailAndPassword(String email, String password);
}
