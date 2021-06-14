package com.example.foodie.service;

import com.example.foodie.dto.OrderDto;
import com.example.foodie.dto.ProductQuantityDto;
import com.example.foodie.entity.Order;
import com.example.foodie.entity.OrderProduct;
import com.example.foodie.entity.Product;
import com.example.foodie.entity.User;
import com.example.foodie.repository.OrderProductRepository;
import com.example.foodie.repository.OrderRepository;
import com.example.foodie.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private OrderProductRepository orderProductRepository;

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
        orderDto = new OrderDto();
        orderDto.setUserId(1L);
        orderDto.getProducts().add(new ProductQuantityDto(product1, 1L));
        orderDto.getProducts().add(new ProductQuantityDto(product2, 1L));
    }

    @Test
    public void shouldCreate() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Order order = orderService.create(orderDto);
        OrderProduct orderProduct1 = new OrderProduct(order, product1, 1);
        OrderProduct orderProduct2 = new OrderProduct(order, product2, 1);
        assertThat(order.getUser()).isEqualTo(user);
        assertThat(order.getOrderProduct().isEmpty()).isFalse();
        assertThat(order.getOrderProduct().contains(orderProduct1)).isTrue();
        assertThat(order.getOrderProduct().contains(orderProduct2)).isTrue();
    }

    @Test
    public void shouldUpdate() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Order order = orderService.create(orderDto);
        assertThat(orderService.update(order)).isEqualTo(order);
    }
}
