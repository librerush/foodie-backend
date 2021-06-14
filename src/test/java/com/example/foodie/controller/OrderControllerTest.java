package com.example.foodie.controller;

import com.example.foodie.dto.OrderDto;
import com.example.foodie.dto.ProductQuantityDto;
import com.example.foodie.entity.Product;
import com.example.foodie.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Test
    public void shouldCreate() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Juice");
        objectMapper = new ObjectMapper();
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(1L);
        orderDto.getProducts().add(new ProductQuantityDto(product, 1L));

        mockMvc.perform(post("/api/order/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isOk());
    }
}
