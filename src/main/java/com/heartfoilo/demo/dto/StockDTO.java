package com.heartfoilo.demo.dto;

import lombok.Data;

@Data
public class StockDTO {
    private Long stockId;
    private String code; // 종목 코드
    private String name; // 종목 이름
    private String sector; // 섹터
    private String symbol; // 영어 줄임말
    private float earningRate; // 수익률
}