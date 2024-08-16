package com.cracre.heartfoilo.domain.stock.repository;

import com.cracre.heartfoilo.domain.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
