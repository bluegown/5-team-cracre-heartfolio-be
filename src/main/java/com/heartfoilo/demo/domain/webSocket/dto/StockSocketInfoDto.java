package com.heartfoilo.demo.domain.webSocket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockSocketInfoDto {

    //종목 코드
    private final String SYMB;

    //cur_cost
    private int LAST;

    //시가
    private int OPEN;

    //high_cost
    private int HIGH;

    //low_cost
    private int low_cost;

    //earningValue
    private int earningValue;

    //earningRate
    private float earningRate;

}
