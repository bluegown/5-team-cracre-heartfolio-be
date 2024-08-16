package com.heartfoilo.demo.domain.portfolio.repository;

import com.heartfoilo.demo.domain.invest.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Account, Long> {

}
