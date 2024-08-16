package com.heartfoilo.demo.domain.invest.repository;

import com.heartfoilo.demo.domain.invest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestRepository extends JpaRepository<Order, Long> {

}

