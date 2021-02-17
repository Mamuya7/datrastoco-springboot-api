package com.mamuya.datrastocospringbootapi.service.serviceImpl;

import com.mamuya.datrastocospringbootapi.entities.StockBalance;
import com.mamuya.datrastocospringbootapi.repository.StockBalanceRepository;
import com.mamuya.datrastocospringbootapi.service.StockBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockBalanceServiceImpl implements StockBalanceService {

    private StockBalanceRepository stockBalanceRepository;

    @Autowired
    public StockBalanceServiceImpl(StockBalanceRepository stockBalanceRepository) {
        this.stockBalanceRepository = stockBalanceRepository;
    }

    @Override
    public StockBalance save(StockBalance stockBalance) {
        return stockBalanceRepository.save(stockBalance);
    }

    @Override
    public StockBalance findById(int id) {
        Optional<StockBalance> stockBalance = stockBalanceRepository.findById(id);
        return stockBalance.orElse(null);
    }

    @Override
    public boolean existsById(int id) {
        return stockBalanceRepository.existsById(id);
    }

    @Override
    public List<StockBalance> findAll() {
        return stockBalanceRepository.findAll();
    }

    @Override
    public long count() {
        return stockBalanceRepository.count();
    }

    @Override
    public void deleteById(int id) {
        stockBalanceRepository.deleteById(id);
    }
}
