package com.cracre.heartfoilo.domain.portfolio.repository;

import com.cracre.heartfoilo.domain.invest.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Account, Long> {

}
