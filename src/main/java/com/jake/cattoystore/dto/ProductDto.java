package com.jake.cattoystore.dto;

import com.github.dozermapper.core.Mapping;

import lombok.Data;

@Data
public class ProductDto {

    @Mapping("name")
    private String name;
}
