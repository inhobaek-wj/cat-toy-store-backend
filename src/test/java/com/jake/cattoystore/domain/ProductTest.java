package com.jake.cattoystore.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    public void createProduct() {
        Product product = Product.builder()
            .name("airforce")
            .maker("nike")
            .price(3000)
            .build();

        assertThat(product.getName()).isEqualTo("airforce");
        assertThat(product.getMaker()).isEqualTo("nike");
        assertThat(product.getPrice()).isEqualTo("3,000");
    }

}
