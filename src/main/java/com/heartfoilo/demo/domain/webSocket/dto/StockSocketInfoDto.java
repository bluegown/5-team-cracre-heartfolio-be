package com.heartfoilo.demo.domain.webSocket.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockSocketInfoDto {

    //종목 코드
    private String symbol;

    //cur_cost 현재가
    private int curPrice;
    //시가
    private int openPrice;

    //high_cost 최고가
    private int highPrice;

    //low_cost 최저가
    private int lowPrice;

    //earningValue 전일대비 수익
    private int earningValue;

    //earningRate 전일대비 수익률
    private float earningRate;

}

