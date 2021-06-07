package com.example.foodie.service;

import com.example.foodie.dto.BrandDto;
import com.example.foodie.dto.ResultDto;
import com.example.foodie.entity.Brand;
import com.example.foodie.repository.BrandRepository;
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
public class BrandServiceTest {
    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    private BrandDto brandDto;

    private Brand brand;

    private ResultDto resultDto;

    @BeforeEach
    public void setUp() {
        brandDto = new BrandDto("Name", "Description");
        brand = new Brand("Name", "Description");
    }

    @Test
    public void shouldCreate() {
        when(brandRepository.save(any())).then(returnsFirstArg());
        assertThat(brandService.create(brandDto)).isEqualTo(brand);
    }

    @Test
    public void shouldDelete() {
        resultDto = new ResultDto(true, String.format("Deleted: %s", brand));
        assertThat(brandService.delete(brand)).isEqualTo(resultDto);
    }

    @Test
    public void shouldDeleteById() {
        resultDto = new ResultDto(true, String.format("Deleted: %s", 1L));
        assertThat(brandService.deleteById(1L)).isEqualTo(resultDto);
    }
}
