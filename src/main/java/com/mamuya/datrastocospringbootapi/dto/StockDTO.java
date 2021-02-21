package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Stock;

public class StockDTO implements DTOValidation<Stock> {

    public StockDTO(Stock stock) {
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
    public Stock createEntity() {
        return null;
    }

    @Override
    public Stock updateEntity(Stock entity) {
        return null;
    }

    @Override
    public boolean hasValid(String item) {
        return false;
    }
}
