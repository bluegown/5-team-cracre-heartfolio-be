package com.cracre.heartfoilo.dto;
import lombok.Data;

@Data
public class RankDTO {
    private Long rankId;
    private Long userId; // User ID, UserDTO와의 관계를 매핑할 수도 있음
    private float sumReturn; // 누적 수익률
    private float monthlyReturn; // 월별 수익률
    private Long donation; // 기부 금액
}