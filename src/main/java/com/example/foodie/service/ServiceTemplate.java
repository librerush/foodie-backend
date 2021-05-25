package com.example.foodie.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface ServiceTemplate<T, I, DTO> {
    @Transactional
    @Modifying
    T create(DTO dto);

    @Transactional
    @Modifying
    void delete(T t);

    @Transactional
    @Modifying
    void deleteById(I i);

    @Transactional
    @Modifying
    T update(T t);

    @Transactional
    Optional<T> findById(I i);

    @Transactional
    T getById(I i) throws ResponseStatusException;

    @Transactional
    List<T> findAll();
}
