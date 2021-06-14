package com.example.foodie.controller;

import com.example.foodie.entity.Brand;
import com.example.foodie.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BrandController.class)
public class BrandControllerTest {
    @MockBean
    private BrandService brandService;

    @Autowired
    private MockMvc mockMvc;

    private final Brand brand = new Brand("foo");

    @Test
    public void shouldCreate() throws Exception {
        String jsonStr = "{\"name\": \"foo\"}";
        when(brandService.create(any())).thenReturn(brand);

        mockMvc.perform(post("/api/brand/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(jsonStr))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.name").value("foo"));
    }

    @Test
    public void shouldFindAll() throws Exception {
        when(brandService.findAll()).thenReturn(Collections.singletonList(brand));

        mockMvc.perform(get("/api/brand/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(0))
                .andExpect(jsonPath("$[0].name").value("foo"));
    }
}
