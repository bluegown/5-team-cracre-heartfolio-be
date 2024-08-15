package com.cracre.heartfoilo.dto;

import lombok.Data;

@Data
public class StockInfoDTO {
    private Long stockInfoId;
    private Long stockId; // Stock ID, StockDTO와의 관계를 매핑할 수도 있음
    private Long totalMarketPrice; // 시가총액
}