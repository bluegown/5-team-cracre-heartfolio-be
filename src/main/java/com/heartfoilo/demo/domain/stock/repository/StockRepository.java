package com.heartfoilo.demo.domain.stock.repository;

import com.heartfoilo.demo.domain.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query("SELECT s FROM Stock s JOIN StockInfo si ON s.id = si.stock.id ORDER BY si.totalMarketPrice DESC")
    List<Stock> findTopStocksByTotalMarketPrice(Pageable pageable);


    List<Stock> findByNameContainingOrSymbolContaining(String nameKeyword, String symbolKeyword);
}

