package com.example.foodie.service;

import com.example.foodie.dto.OrderDto;
import com.example.foodie.entity.Order;
import com.example.foodie.entity.Product;
import com.example.foodie.entity.User;
import com.example.foodie.repository.OrderRepository;
import com.example.foodie.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderService orderService;

    @Mock
    private Product product1;

    @Mock
    private Product product2;

    @Mock
    private User user;

    private OrderDto orderDto;

    @BeforeEach
    public void setUp() {
        when(orderRepository.save(any())).then(returnsFirstArg());
        orderDto = new OrderDto(1L, Arrays.asList(product1, product2));
    }

    @Test
    public void shouldCreate() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertThat(product1).isIn(orderService.create(orderDto).getProducts());
        assertThat(product2).isIn(orderService.create(orderDto).getProducts());
    }

    @Test
    public void shouldUpdate() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Order order = orderService.create(orderDto);
        assertThat(orderService.update(order)).isEqualTo(order);
    }
}
