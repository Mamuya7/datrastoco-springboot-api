package com.mamuya.datrastocospringbootapi.dto;

import com.mamuya.datrastocospringbootapi.entities.Purchase;

public class PurchaseDTO implements DTOValidation<Purchase> {

    public PurchaseDTO(Purchase purchase) {
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
    public Purchase createEntity() {
        return null;
    }

    @Override
    public Purchase updateEntity(Purchase entity) {
        return null;
    }

    @Override
    public boolean hasValid(String item) {
        return false;
    }
}
