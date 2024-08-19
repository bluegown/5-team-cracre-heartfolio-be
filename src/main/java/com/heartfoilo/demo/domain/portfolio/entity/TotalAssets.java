package com.heartfoilo.demo.domain.portfolio.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalAssets {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "stock_id", nullable = false)
    private Long stockId;

    @Column(name = "total_quantity", nullable = false)
    private Long totalQuantity;

    @Column(name = "purchase_avg_price", nullable = false)
    private Long purchaseAvgPrice;
}
