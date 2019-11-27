package com.jake.cattoystore.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    public void createProduct() {
        Product product = new Product();
        product.setName("airforce");
        product.setMaker("nike");
        product.setPrice(3000);

        assertThat(product.getName()).isEqualTo("airforce");
        assertThat(product.getMaker()).isEqualTo("nike");
        assertThat(product.getPrice()).isEqualTo(4000);
    }
}
