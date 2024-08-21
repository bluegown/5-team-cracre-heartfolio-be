package com.heartfoilo.demo.domain.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.heartfoilo.demo.domain.portfolio.entity.Account;
public interface PortfolioRepository extends JpaRepository<Account, Long> {

}
