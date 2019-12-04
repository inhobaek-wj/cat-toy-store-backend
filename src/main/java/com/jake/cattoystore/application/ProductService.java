package com.jake.cattoystore.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.jake.cattoystore.domain.Product;
import com.jake.cattoystore.domain.ProductRepository;
import com.jake.cattoystore.dto.ProductDto;

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
        return productRepository.findById(id).get();
    }

    public Product updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).get();

        product.updateWithDto(productDto);

        return product;
    }
}
