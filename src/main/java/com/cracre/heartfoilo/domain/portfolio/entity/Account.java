package com.cracre.heartfoilo.domain.portfolio.entity;


import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long Id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "cash", nullable = false)
    private Long cash;

    @Column(name = "total_purchase", nullable = false)
    private Long totalPurchase;
}