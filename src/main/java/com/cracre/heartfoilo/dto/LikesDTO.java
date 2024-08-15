package com.cracre.heartfoilo.dto;
import lombok.Data;

@Data
public class LikesDTO {
    private Long likesId;
    private Long userId; // User ID, UserDTO와의 관계를 매핑할 수도 있음
    private Long stockId; // Stock ID, StockDTO와의 관계를 매핑할 수도 있음
}