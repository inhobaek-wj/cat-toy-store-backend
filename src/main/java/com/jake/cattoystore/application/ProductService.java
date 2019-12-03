package com.jake.cattoystore.application;

import java.util.ArrayList;
import java.util.List;

import com.jake.cattoystore.domain.Greeting;
import com.jake.cattoystore.domain.Product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return new ArrayList<>(this.products);
    }

    public void addProduct(String name) {
        products.add(Product.builder().name(name).build());
    }

}
