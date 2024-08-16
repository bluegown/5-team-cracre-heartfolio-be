package com.heartfoilo.demo.dto;
import lombok.Data;

@Data
public class TotalAssetsDTO {
    private Long id;
    private Long userId; // User ID, UserDTO와의 관계를 매핑할 수도 있음
    private Long stockId; // Stock ID, StockDTO와의 관계를 매핑할 수도 있음
    private Long totalQuantity; // 보유수량
    private Long purchaseAvgPrice; // 매수평단가
}