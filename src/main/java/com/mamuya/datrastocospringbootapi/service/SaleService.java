package com.mamuya.datrastocospringbootapi.service;

import com.mamuya.datrastocospringbootapi.entities.Sale;

import java.util.List;

public interface SaleService {
    Sale save(Sale sale);

    Sale findById(int id);

    boolean existsById(int id);

    List<Sale> findAll();

    long count();

    void deleteById(int id);
}
