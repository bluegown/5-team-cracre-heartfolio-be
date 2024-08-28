package com.heartfoilo.demo.domain.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.heartfoilo.demo.domain.portfolio.entity.Account;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(long userId);
}
