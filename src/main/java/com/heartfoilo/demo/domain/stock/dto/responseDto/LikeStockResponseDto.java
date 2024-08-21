package com.heartfoilo.demo.domain.stock.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeStockResponseDto {
    private Long stockId;
    private String stockName;
    private int currentPrice;  // 현재가 필드 추가
    private Integer earningValue;
    private float earningRate;
}
