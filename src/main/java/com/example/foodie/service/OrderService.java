package com.example.foodie.service;

import com.example.foodie.dto.OrderDto;
import com.example.foodie.dto.ResultDto;
import com.example.foodie.entity.Order;
import com.example.foodie.entity.Product;
import com.example.foodie.entity.User;
import com.example.foodie.repository.OrderRepository;
import com.example.foodie.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements ServiceTemplate<Order, Long, OrderDto> {
    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order create(OrderDto orderDto) {
        Long id = orderDto.getUserId();
        List<Product> productList = orderDto.getProducts();
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id: " + id);
        }
        User user = userOptional.get();
        Order order = new Order(LocalDateTime.now(), user);
        order.setProducts(productList);
        return orderRepository.save(order);
    }

    @Override
    public ResultDto delete(Order order) {
        try {
            orderRepository.delete(order);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + order);
        }
        return new ResultDto(true, "Deleted order: " + order);
    }

    @Override
    public ResultDto deleteById(Long aLong) {
        try {
            orderRepository.deleteById(aLong);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order id not found: " + aLong);
        }
        return new ResultDto(true, "Deleted order with id: " + aLong);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Order getById(Long id) throws ResponseStatusException {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No order with id: " + id);
    }

    @Override
    public List<Order> findAll() {
        return null;
    }
}
