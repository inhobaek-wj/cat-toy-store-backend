package com.jake.cattoystore.application;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.jake.cattoystore.domain.Greeting;
import com.jake.cattoystore.domain.Product;
import com.jake.cattoystore.domain.ProductRepository;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProduct(Long id) {
        return null;
    }

}
