package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Product;

public class ProductDTO implements DTOValidation<Product>{

    public ProductDTO(Product productt) {
    }

    @Override
    public boolean hasAllValidMappings() {
        return false;
    }

    @Override
    public boolean hasAnyValidMappings() {
        return false;
    }

    @Override
    public Product createEntity() {
        return null;
    }

    @Override
    public Product updateEntity(Product entity) {
        return null;
    }

    @Override
    public boolean hasValid(String item) {
        return false;
    }
}
