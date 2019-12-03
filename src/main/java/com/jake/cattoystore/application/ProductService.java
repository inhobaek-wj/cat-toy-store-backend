package com.jake.cattoystore.application;

import java.util.ArrayList;
import java.util.List;

import com.jake.cattoystore.domain.Greeting;
import com.jake.cattoystore.domain.Product;
import com.jake.cattoystore.domain.ProductRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(String name) {
        Product product = Product.builder().name(name).build();

        productRepository.save(product);
    }

}
