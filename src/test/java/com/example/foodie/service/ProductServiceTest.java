package com.example.foodie.service;

import com.example.foodie.dto.ProductDto;
import com.example.foodie.repository.BrandRepository;
import com.example.foodie.repository.CategoryRepository;
import com.example.foodie.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductDto productDto;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void shouldCreate() {
        when(productRepository.save(any())).then(returnsFirstArg());
        assertThat(productService.create(productDto)).isNotNull();
    }

    @Test
    public void shouldFindAll() {
        assertThat(productService.findAll()).isNotNull();
    }
}
