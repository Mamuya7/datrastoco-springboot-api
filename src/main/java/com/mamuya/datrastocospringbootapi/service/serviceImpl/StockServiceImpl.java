package com.mamuya.datrastocospringbootapi.service.serviceImpl;

import com.mamuya.datrastocospringbootapi.entities.Stock;
import com.mamuya.datrastocospringbootapi.repository.StockRepository;
import com.mamuya.datrastocospringbootapi.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock findById(int id) {
        Optional<Stock> stock = stockRepository.findById(id);
        return stock.orElse(null);
    }

    @Override
    public boolean existsById(int id) {
        return stockRepository.existsById(id);
    }

    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public long count() {
        return stockRepository.count();
    }

    @Override
    public void deleteById(int id) {
        stockRepository.deleteById(id);
    }
}
