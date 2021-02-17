package com.mamuya.datrastocospringbootapi.service;

import com.mamuya.datrastocospringbootapi.entities.Stock;

import java.util.List;

public interface StockService {
    Stock save(Stock stock);

    Stock findById(int id);

    boolean existsById(int id);

    List<Stock> findAll();

    long count();

    void deleteById(int id);
}
