package com.jake.cattoystore.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Product {

    @Getter
    private String name;

    @Getter
    private String maker;

    @Getter
    private Integer price;
}
