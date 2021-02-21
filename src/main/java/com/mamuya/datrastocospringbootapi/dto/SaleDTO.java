package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Sale;

public class SaleDTO implements DTOValidation<Sale> {

    public SaleDTO(Sale sale) {
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
    public Sale createEntity() {
        return null;
    }

    @Override
    public Sale updateEntity(Sale entity) {
        return null;
    }

    @Override
    public boolean hasValid(String item) {
        return false;
    }
}
