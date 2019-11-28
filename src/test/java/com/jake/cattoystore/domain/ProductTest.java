package com.jake.cattoystore.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {


    // for multiple tests, set product as a member.
    private Product product;

    @BeforeEach
    public void initProduct() {
        product = Product.builder()
            .name("airforce")
            .maker("nike")
            .price(3000)
            .build();
    }

    @Test
    public void createProduct() {

        assertThat(product.getName()).isEqualTo("airforce");
        assertThat(product.getMaker()).isEqualTo("nike");

        // first, make test what I need.
        assertThat(product.getPriceWithComma()).isEqualTo("3,000");
    }

    @Test
    public void defaultImage() {
        assertThat(product.getImageUrl()).isEqualTo("");
    }
}
