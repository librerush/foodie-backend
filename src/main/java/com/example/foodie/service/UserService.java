package com.example.foodie.service;

import com.example.foodie.dto.ResultDto;
import com.example.foodie.dto.UserDto;
import com.example.foodie.entity.Order;
import com.example.foodie.entity.User;
import com.example.foodie.repository.OrderRepository;
import com.example.foodie.repository.UserRepository;
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
    private final PasswordEncoderService passwordEncoderService;

    public UserService(UserRepository userRepository, OrderRepository orderRepository, PasswordEncoderService passwordEncoderService) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.passwordEncoderService = passwordEncoderService;
    }

    @Override
    public User create(UserDto userDto) {
        Optional<User> userOptional = userRepository.findUserByEmail(userDto.getEmail());
        if (userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with email already exists: " + userDto.getEmail());
        }
        User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoderService.encode(userDto.getPassword()), userDto.getAddress());
        return userRepository.save(user);
    }

    @Override
    public ResultDto delete(User user) {
        try {
            userRepository.delete(user);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found: " + user);
        }
        return new ResultDto(true, "Deleted user: " + user);
    }

    @Override
    public ResultDto deleteById(Long aLong) {
        userRepository.deleteById(aLong);
        return new ResultDto();
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

    @Transactional
    public boolean exists(String email, String password) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isEmpty()) {
            return false;
        }
        return passwordEncoderService.matches(password, userOptional.get().getPassword());
    }
}
