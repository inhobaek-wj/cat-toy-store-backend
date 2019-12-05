package com.jake.cattoystore.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.jake.cattoystore.application.ProductService;
import com.jake.cattoystore.domain.Product;
import com.jake.cattoystore.dto.ProductDto;

import static org.hamcrest.CoreMatchers.containsString;

// it takes a little bit long to test controller.

@ExtendWith(SpringExtension.class) // junit5 <- @RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
public class ProductControllerTest {

    private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9."
        + "eyJ1c2VySWQiOjEzLCJuYW1lIjoidGVzdGVyIn0."
        + "s7-SwQW2LsfmFjEQGQbsJciJB1R7rvoDGvpRXpfFJJA";

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

    @Test
    public void createWithoutAuthentication() throws Exception {
        mockMvc.perform(
                        post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"airforce\",\"maker\":\"NIKE\", \"price\":50000}")
                        )
            .andExpect(status().isUnauthorized());
    }

    @Test
    public void createWithValidAttributes() throws Exception {
        // mockMvc.perform(post("/products"))
        //     .andExpect(status().isCreated());

        Product product = Product.builder().id(13L).build();

        given(productService.addProduct(any())).willReturn(product);

        mockMvc.perform(
                        post("/products")
                        .header("Authorization", "Bearer " + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"airforce\",\"maker\":\"NIKE\", \"price\":50000}")
                        )
            .andExpect(status().isCreated())
            .andExpect(header().string("location", "/products/13"));

        // verify if a product is added in the real controller.
        // verify(productService).addProduct("airforce", "NIKE", 50000);

        verify(productService).addProduct(any(Product.class));
    }

    @Test
    public void createWithInvalidAttributes() throws Exception {
        // this test failed as NullPointer
        // when I put @Valid anotation into the controller
        // and didn't set vaildation anotation into dto object.

        mockMvc.perform(
                        post("/products")
                        // .header("Authorization", "Bearer " + TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\" \",\"maker\":\"NIKE\", \"price\":50000}")
                        )
            .andExpect(status().isBadRequest());

    }

    @Test
    public void destroy() throws Exception {
        mockMvc.perform(delete("/products/13"))
            .andExpect(status().isOk());

        // => Wanted but not invoked:
        verify(productService).removeProduct(13L);
    }


    @Test
    public void detailWhenProductExists() throws Exception {

        Product product = Product.builder()
            .name("airforce")
            .maker("NIKE")
            .price(50000)
            .build();

        given(productService.getProduct(13L)).willReturn(product);

        mockMvc.perform(get("/products/13"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("airforce")));

        verify(productService).getProduct(13L);
    }

    @Test
    public void detailWhenProductNotExists() throws Exception {

        given(productService.getProduct(13L)).willThrow(new EntityNotFoundException());

        mockMvc.perform(get("/products/13"))
            .andExpect(status().isNotFound());
    }


    @Test
    public void updateWithVaildAttributes() throws Exception {
        mockMvc.perform(
                        patch("/products/13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"jordan\",\"maker\":\"NIKE\", \"price\":100000}")
                        )
            .andExpect(status().isOk());

        ProductDto productDto = ProductDto.builder()
            .name("jordan")
            .maker("NIKE")
            .price(100000)
            .build();

        verify(productService).updateProduct(13L, productDto);
    }

    @Test
    public void updateWithInvaildAttributes() throws Exception {
        mockMvc.perform(
                        patch("/products/13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"maker\":\"NIKE\", \"price\":0}")
                        )
            .andExpect(status().isBadRequest());
    }
}
