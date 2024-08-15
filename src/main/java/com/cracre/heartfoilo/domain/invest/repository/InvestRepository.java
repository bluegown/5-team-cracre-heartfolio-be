package com.cracre.heartfoilo.domain.invest.repository;

import com.cracre.heartfoilo.domain.invest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestRepository extends JpaRepository<Order, Long> {

}

