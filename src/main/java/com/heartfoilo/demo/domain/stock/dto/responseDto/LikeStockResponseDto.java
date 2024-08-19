package com.heartfoilo.demo.domain.stock.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeStockResponseDto {
    private Long stockId;
    private String stockCode;
    private String stockName;
    private Integer earningValue;
    private float earningRate;
}
