package com.heartfoilo.demo.domain.portfolio.repository;

import com.heartfoilo.demo.domain.portfolio.entity.TotalAssets;
import com.heartfoilo.demo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;


public interface TotalAssetsRepository extends JpaRepository<TotalAssets, Long> {
    List<TotalAssets> findByUserId(long userId);
    TotalAssets findByStockId(long stockId);

    Optional<TotalAssets> findByUserIdAndStockId(long userId, long stockId);

}
