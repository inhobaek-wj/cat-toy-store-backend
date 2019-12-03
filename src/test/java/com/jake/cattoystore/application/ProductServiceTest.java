package com.jake.cattoystore.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import com.jake.cattoystore.application.ProductService;

public class ProductServiceTest {


    // for multiple tests, set product as a member.
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        productService = new ProductService();
    }

    @Test
    public void getProduct() {
        assertThat(productService.getProducts()).isEmpty();
    }

    @Test
    public void addProduct() {
        productService.addProduct("airforce");

        assertThat(productService.getProducts()).isNotEmpty();
    }

}
