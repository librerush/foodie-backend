package com.example.foodie.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
public class PasswordEncoderServiceTest {
    @InjectMocks
    private PasswordEncoderService passwordEncoderService;

    @Test
    public void shouldMatchRawAndEncodedPassword() {
        String password1 = "password";
        String password2 = "password";

        String encoded1 = passwordEncoderService.encode(password1);
        String encoded2 = passwordEncoderService.encode(password2);

        assertThat(passwordEncoderService.matches(password1, encoded1)).isTrue();
        assertThat(passwordEncoderService.matches(password2, encoded2)).isTrue();
        assertThat(passwordEncoderService.matches(password1, encoded2)).isTrue();
    }
}
