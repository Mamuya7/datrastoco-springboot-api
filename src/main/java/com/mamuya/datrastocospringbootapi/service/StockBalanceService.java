package com.mamuya.datrastocospringbootapi.service;

import com.mamuya.datrastocospringbootapi.entities.StockBalance;

import java.util.List;

public interface StockBalanceService {
    StockBalance save(StockBalance stockBalance);

    StockBalance findById(int id);

    boolean existsById(int id);

    List<StockBalance> findAll();

    long count();

    void deleteById(int id);
}
