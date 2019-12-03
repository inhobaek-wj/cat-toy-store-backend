package com.jake.cattoystore.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.jake.cattoystore.application.ProductService;
import com.jake.cattoystore.domain.Product;

import static org.hamcrest.CoreMatchers.containsString;

// it takes a little bit long to test controller.

@ExtendWith(SpringExtension.class) // junit5 <- @RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@ActiveProfiles("myTest")
public class ProductControllerTest {

    // MockMvc object is injected by Spring IoC Container.
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void mockProductService() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().name("airforce").build());

        given(productService.getProducts()).willReturn(products);
    }

    // 404.
    // contents missing.
    // use application layer -> MockBean
    // verify
    @Test
    public void list() throws Exception {
        mockMvc.perform(get("/products"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("airforce")));

        verify(productService).getProducts();
    }
}
