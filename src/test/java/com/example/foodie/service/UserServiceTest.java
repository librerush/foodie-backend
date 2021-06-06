package com.example.foodie.service;

import com.example.foodie.dto.UserDto;
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

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PasswordEncoderService passwordEncoderService;

    @InjectMocks
    private UserService userService;

    private final UserDto userDto = new UserDto("foo", "foo@mail.com", "pass", "USA");
    private User user;

    @BeforeEach
    public void setUp() {
        passwordEncoderService = new PasswordEncoderService();
        userService = new UserService(userRepository, orderRepository, passwordEncoderService);
        when(userRepository.save(any())).then(returnsFirstArg());
        user = userService.create(userDto);
    }

    @Test
    public void shouldCreateAndMatch() {
        assertThat(passwordEncoderService.matches("pass", user.getPassword())).isTrue();
        assertThat(user.getEmail()).isEqualTo(userDto.getEmail());
    }

    @Test
    public void shouldUpdate() {
        user.setId(1L);
        assertThat(userService.update(user)).isEqualTo(user);
    }

    @Test
    public void shouldExistsByEmailAndPassword() {
        when(userRepository.findUserByEmail("foo@mail.com")).thenReturn(Optional.of(user));
        assertThat(userService.exists("foo@mail.com", "pass")).isTrue();
        assertThat(userService.getByEmail("foo@mail.com")).isEqualTo(user);
    }
}
