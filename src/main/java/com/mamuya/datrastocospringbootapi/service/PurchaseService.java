package com.mamuya.datrastocospringbootapi.service;

import com.mamuya.datrastocospringbootapi.entities.Purchase;

import java.util.List;

public interface PurchaseService {
    Purchase save(Purchase purchase);

    Purchase findById(int id);

    boolean existsById(int id);

    List<Purchase> findAll();

    long count();

    void deleteById(int id);
}
