package com.heartfoilo.demo.domain.stock.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAmountResponseDto {

    private String symbol;
    private String name;
    private Long amount;
    private boolean isLikePresent;


}
