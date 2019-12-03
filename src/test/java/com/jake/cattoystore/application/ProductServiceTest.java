package com.jake.cattoystore.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import com.jake.cattoystore.application.ProductService;
import com.jake.cattoystore.domain.Product;
import com.jake.cattoystore.domain.ProductRepository;

public class ProductServiceTest {


    // for multiple tests, set product as a member.
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        // productService = new ProductService();

        MockitoAnnotations.initMocks(this);

        productService = new ProductService(productRepository);
    }

    @Test
    public void getProductsWithEmpty() {
        List<Product> products = new ArrayList<>();

        given(productRepository.findAll()).willReturn(products);

        assertThat(productService.getProducts()).isEmpty();
    }

    @Test
    public void addProduct() {
        productService.addProduct("airforce", "NIKE", 50000);

        // assertThat(productService.getProducts()).isNotEmpty();

        verify(productRepository).save(any());
    }

    @Test
    public void getProductsWithOneProduct() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().name("airforce").build());

        given(productRepository.findAll()).willReturn(products);

        assertThat(productService.getProducts()).isNotEmpty();

    }

}
