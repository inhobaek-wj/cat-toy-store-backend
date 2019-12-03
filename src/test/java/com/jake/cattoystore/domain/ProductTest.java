package com.jake.cattoystore.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.jake.cattoystore.dto.ProductDto;

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

    @Test
    public void changeImage() {
        String newImageUrl = "newImageUrl";

        product.changeImageUrl(newImageUrl);

        assertThat(product.getImageUrl()).isEqualTo(newImageUrl);
    }

    @Test
    public void mapFromDTO() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        ProductDto productDto = new ProductDto();
        productDto.setName("airforce");

        // apply dozermapper.
        // DTO -> domain model.
        // in order to this, you need to @Mapping anotaion in Dto class.
        Product product = mapper.map(productDto, Product.class);

        assertThat(product.getName()).isEqualTo("airforce");
    }
}
