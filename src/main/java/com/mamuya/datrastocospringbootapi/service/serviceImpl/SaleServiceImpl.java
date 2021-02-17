package com.mamuya.datrastocospringbootapi.service.serviceImpl;

import com.mamuya.datrastocospringbootapi.entities.Sale;
import com.mamuya.datrastocospringbootapi.repository.SaleRepository;
import com.mamuya.datrastocospringbootapi.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Sale findById(int id) {
        Optional<Sale> sale = saleRepository.findById(id);
        return sale.orElse(null);
    }

    @Override
    public boolean existsById(int id) {
        return saleRepository.existsById(id);
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public long count() {
        return saleRepository.count();
    }

    @Override
    public void deleteById(int id) {
        saleRepository.deleteById(id);
    }
}
