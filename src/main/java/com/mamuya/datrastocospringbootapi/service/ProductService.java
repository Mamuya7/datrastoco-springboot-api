package com.mamuya.datrastocospringbootapi.service;

import com.mamuya.datrastocospringbootapi.entities.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    Product findById(int id);

    boolean existsById(int id);

    List<Product> findAll();

    long count();

    void deleteById(int id);
}
