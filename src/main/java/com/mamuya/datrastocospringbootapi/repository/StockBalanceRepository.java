package com.mamuya.datrastocospringbootapi.repository;

import com.mamuya.datrastocospringbootapi.entities.StockBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockBalanceRepository extends JpaRepository<StockBalance, Integer> {
}
