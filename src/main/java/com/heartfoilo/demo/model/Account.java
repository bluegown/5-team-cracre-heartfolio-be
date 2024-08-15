package com.heartfoilo.demo.model;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User와 다대일 관계

    @Column(nullable = false)
    private Long cash; // 보유 캐시

    @Column(nullable = false)
    private Long totalPurchase; // 총 매수 금액
}