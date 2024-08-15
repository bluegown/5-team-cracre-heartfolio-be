package com.heartfoilo.demo.model;


import jakarta.persistence.*;
import lombok.*;
@Data
@Entity
@Getter
@Setter
@Table(name = "rank")
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rankId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User와 다대일 관계

    @Column(nullable = false)
    private float sumReturn; // 누적 수익률

    @Column(nullable = false)
    private float monthlyReturn; // 월별 수익률

    @Column(nullable = false)
    private Long donation; // 기부 금액
}
