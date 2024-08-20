package com.heartfoilo.demo.domain.stock.repository;

import com.heartfoilo.demo.domain.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}