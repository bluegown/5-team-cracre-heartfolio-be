package com.cracre.heartfoilo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long orderId;
    private Long userId; // User ID, UserDTO와의 관계를 매핑할 수도 있음
    private String orderCategory; // buy, sell로 구분
    private LocalDateTime orderDate;
    private int orderAmount; // 수량
    private int orderPrice; // 평단가
    private Long totalAmount; // 체결금액 = 체결단가 * 수량
    private Long stockId; // Stock ID, StockDTO와의 관계를 매핑할 수도 있음
}