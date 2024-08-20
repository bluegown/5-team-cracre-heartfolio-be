package com.heartfoilo.demo.domain.webSocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockSocketInfoDto {

    //종목 코드
    private String SYMB;

    //cur_cost 현재가
    private int LAST;

    //시가
    private int OPEN;

    //high_cost 최고가
    private int HIGH;

    //low_cost 최저가
    private int low_cost;

    //earningValue 전일대비 수익
    private int earningValue;

    //earningRate 전일대비 수익률
    private float earningRate;

}
