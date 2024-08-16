package com.heartfoilo.demo.domain.stock.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class FavoriteStockResponseDto {
    private Long stockId;
    private String stockCode;
    private String stockName;
}
