package com.heartfoilo.demo.model;
import lombok.Data;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @Column(nullable = false, length = 64)
    private String code; // 종목 코드

    @Column(nullable = false, length = 48)
    private String name; // 종목 이름

    @Column(nullable = false, length = 64)
    private String sector; // 섹터

    @Column(nullable = false, length = 64)
    private String symbol; // 영어 줄임말

    @Column(nullable = false)
    private float earningRate; // 수익률
}