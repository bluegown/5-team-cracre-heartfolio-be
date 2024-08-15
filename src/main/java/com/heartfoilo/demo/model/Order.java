package com.heartfoilo.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User와 다대일 관계

    @Column(nullable = false, length = 4)
    private String orderCategory; // buy, sell로 구분

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private int orderAmount; // 수량

    @Column(nullable = false)
    private int orderPrice; // 평단가

    @Column(nullable = false)
    private Long totalAmount; // 체결금액 = 체결단가 * 수량

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock; // Stock과 다대일 관계
}