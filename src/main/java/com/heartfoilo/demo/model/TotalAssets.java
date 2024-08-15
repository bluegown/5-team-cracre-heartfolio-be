package com.heartfoilo.demo.model;
import jakarta.persistence.*;
import lombok.*;
@Data
@Entity
@Table(name = "total_assets")
public class TotalAssets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User와 다대일 관계

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock; // Stock과 다대일 관계

    @Column(nullable = false)
    private Long totalQuantity; // 보유수량

    @Column(nullable = false)
    private Long purchaseAvgPrice; // 매수평단가
}