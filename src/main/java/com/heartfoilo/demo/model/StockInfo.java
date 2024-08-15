package com.heartfoilo.demo.model;
import jakarta.persistence.*;
import lombok.*;
@Data
@Entity
@Getter
@Setter
@Table(name = "stock_info")
public class StockInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockInfoId;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock; // Stock과 다대일 관계

    @Column(nullable = false)
    private Long totalMarketPrice; // 시가총액
}
