package com.jake.cattoystore.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.dozermapper.core.Mapper;
import com.jake.cattoystore.application.ProductService;
import com.jake.cattoystore.domain.Product;
import com.jake.cattoystore.dto.ProductDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        // Domain model -> DTO mapping.
        return products.stream()
            .map(product -> mapper.map(product, ProductDto.class))
            .collect(Collectors.toList());
    }


    @PostMapping("/products")
    public ResponseEntity<?> create() throws URISyntaxException {
        String name = "airforce";
        String maker = "NIKE";
        Integer price = 50000;
        productService.addProduct(name, maker, price);

        URI location = new URI("/products/1004");

        return ResponseEntity.created(location).build();
    }

}
