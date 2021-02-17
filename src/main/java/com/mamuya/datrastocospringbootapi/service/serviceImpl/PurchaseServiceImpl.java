package com.mamuya.datrastocospringbootapi.service.serviceImpl;

import com.mamuya.datrastocospringbootapi.entities.Purchase;
import com.mamuya.datrastocospringbootapi.repository.PurchaseRepository;
import com.mamuya.datrastocospringbootapi.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Purchase save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public Purchase findById(int id) {
        Optional<Purchase> purchase = purchaseRepository.findById(id);
        return purchase.orElse(null);
    }

    @Override
    public boolean existsById(int id) {
        return purchaseRepository.existsById(id);
    }

    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public long count() {
        return purchaseRepository.count();
    }

    @Override
    public void deleteById(int id) {
        purchaseRepository.deleteById(id);
    }
}
