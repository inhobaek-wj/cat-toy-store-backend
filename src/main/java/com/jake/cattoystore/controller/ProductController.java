package com.jake.cattoystore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.dozermapper.core.Mapper;
import com.jake.cattoystore.application.ProductService;
import com.jake.cattoystore.domain.Product;
import com.jake.cattoystore.dto.ProductDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    Mapper mapper;

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> list() {
        List<Product> products = productService.getProducts();

        // return products.stream().map(product -> {
        //         ProductDto productDto = new ProductDto();
        //         productDto.setName(product.getName());
        //         return productDto;
        //     }).collect(Collectors.toList());

        // apply dozermapper.
        return products.stream()
            .map(product -> mapper.map(product, ProductDto.class))
            .collect(Collectors.toList());
    }
}
