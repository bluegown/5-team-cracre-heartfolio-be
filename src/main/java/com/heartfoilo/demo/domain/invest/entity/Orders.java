package com.heartfoilo.demo.domain.invest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heartfoilo.demo.domain.stock.entity.Stock;
import com.heartfoilo.demo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Long id;


    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Column(nullable = false, length = 4)
    private String orderCategory;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private Long orderAmount;

    @Column(nullable = false)
    private Long orderPrice;

    @Column(nullable = false)
    private Long totalAmount;


    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    @JsonIgnore
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}