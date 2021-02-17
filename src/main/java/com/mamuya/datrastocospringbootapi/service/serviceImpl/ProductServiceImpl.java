package com.mamuya.datrastocospringbootapi.service.serviceImpl;

import com.mamuya.datrastocospringbootapi.entities.Product;
import com.mamuya.datrastocospringbootapi.repository.ProductRepository;
import com.mamuya.datrastocospringbootapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {

        return productRepository.save(product);
    }

    @Override
    public Product findById(int id) {
        Optional<Product> product = productRepository.findById(id);
        return  product.orElse(null);
    }

    @Override
    public boolean existsById(int id) {
        return productRepository.existsById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
}
