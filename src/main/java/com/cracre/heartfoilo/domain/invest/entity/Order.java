package com.cracre.heartfoilo.domain.invest.entity;

import com.cracre.heartfoilo.domain.stock.entity.Stock;
import com.cracre.heartfoilo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 4)
    private String orderCategory;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private int orderAmount;

    @Column(nullable = false)
    private int orderPrice;

    @Column(nullable = false)
    private Long totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;
}