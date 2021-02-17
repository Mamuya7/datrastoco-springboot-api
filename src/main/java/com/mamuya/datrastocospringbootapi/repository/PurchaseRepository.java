package com.mamuya.datrastocospringbootapi.repository;

import com.mamuya.datrastocospringbootapi.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
