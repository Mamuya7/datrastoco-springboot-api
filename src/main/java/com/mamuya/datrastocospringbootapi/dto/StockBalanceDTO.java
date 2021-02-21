package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.StockBalance;

public class StockBalanceDTO implements DTOValidation<StockBalance> {

    public StockBalanceDTO(StockBalance stockBalance) {
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
    public StockBalance createEntity() {
        return null;
    }

    @Override
    public StockBalance updateEntity(StockBalance entity) {
        return null;
    }

    @Override
    public boolean hasValid(String item) {
        return false;
    }
}
