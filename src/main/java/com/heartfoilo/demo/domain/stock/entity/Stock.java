package com.heartfoilo.demo.domain.stock.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long id;

    @Column(nullable = false, length = 64)
    private String code;


    @Column(nullable = false, length = 48)
    private String name;

    @Column(nullable = false, length = 64)
    private String sector;

    @Column(nullable = false, length = 64)
    private String symbol;

    @Column(name = "earning_rate", nullable = false)
    private float earningRate;
}