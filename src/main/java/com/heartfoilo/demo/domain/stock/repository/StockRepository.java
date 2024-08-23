package com.heartfoilo.demo.domain.stock.repository;

import com.heartfoilo.demo.domain.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findAllByOrderByEarningRateDesc(Pageable pageable);

    Stock findById(long stockId);

    List<Stock> findByNameContainingOrSymbolContaining(String nameKeyword, String symbolKeyword);
}

