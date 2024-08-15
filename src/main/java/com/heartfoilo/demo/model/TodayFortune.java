package com.heartfoilo.demo.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "today_fortune")
@Getter
@Setter
public class TodayFortune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todayFortuneId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // User와 다대일 관계

    @ManyToOne
    @JoinColumn(name = "fortune_id", nullable = false)
    private Fortune fortune; // Fortune과 다대일 관계

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성 시간
}