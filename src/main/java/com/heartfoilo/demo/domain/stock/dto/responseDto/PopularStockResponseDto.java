package com.heartfoilo.demo.domain.stock.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PopularStockResponseDto {
    private Long stockId;
    private String stockName;
    private float earningRate;
}
