package com.cracre.heartfoilo.dto;
import lombok.Data;

@Data
public class AccountDTO {
    private Long accountId;
    private Long userId; // User ID, UserDTO와의 관계를 매핑할 수도 있음
    private Long cash; // 보유 캐시
    private Long totalPurchase; // 총 매수 금액
}