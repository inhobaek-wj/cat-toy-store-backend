package com.jake.cattoystore.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAll();

    Product save(Product product);

    void deleteById(Long id);

    Optional<Product> findById(Long id);
}
