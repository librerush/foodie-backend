package com.example.foodie.service;

import com.example.foodie.dto.UserDto;
import com.example.foodie.entity.Order;
import com.example.foodie.entity.User;
import com.example.foodie.repository.OrderRepository;
import com.example.foodie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ServiceTemplate<User, Long, UserDto> {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public UserService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public User create(UserDto userDto) {
        Optional<User> userOptional = userRepository.findUserByEmail(userDto.getEmail());
        if (userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with email already exists: " + userDto.getEmail());
        }
        User user = new User(userDto.getName(), userDto.getEmail(), userDto.getPassword(), userDto.getAddress());
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return userRepository.findById(aLong);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public List<Order> getOrdersById(Long id) {
        List<Order> orders = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return orderRepository.findOrderByUser(user.get());
        }
        return orders;
    }

    @Transactional
    public List<Order> getOrdersByEmail(String email) {
        List<Order> orders = new ArrayList<>();
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            return orderRepository.findOrderByUser(user.get());
        }
        return orders;
    }

    @Override
    public User getById(Long id) throws ResponseStatusException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with id: " + id);
    }

    @Transactional
    public User getByEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user with email: " + email);
    }
}
