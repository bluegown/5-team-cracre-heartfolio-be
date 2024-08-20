package com.heartfoilo.demo.domain.portfolio.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TotalAssets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
