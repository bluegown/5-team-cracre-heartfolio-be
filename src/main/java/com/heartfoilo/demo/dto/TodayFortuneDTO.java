package com.heartfoilo.demo.dto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodayFortuneDTO {
    private Long todayFortuneId;
    private Long userId; // User ID, UserDTO와의 관계를 매핑할 수도 있음
    private Long fortuneId; // Fortune ID, FortuneDTO와의 관계를 매핑할 수도 있음
    private LocalDateTime createdAt; // 생성 시간
}