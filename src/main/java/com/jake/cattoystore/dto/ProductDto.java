package com.jake.cattoystore.dto;

import com.github.dozermapper.core.Mapping;

import lombok.Data;

@Data
public class ProductDto {

    @Mapping("id")
    private Long id;

    @Mapping("name")
    private String name;

    @Mapping("maker")
    private String maker;

    @Mapping("price")
    private Integer price;


}
