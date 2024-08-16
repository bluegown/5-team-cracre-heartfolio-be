package com.heartfoilo.demo.dto;
import lombok.Data;

@Data
public class FortuneDTO {
    private Long fortuneId;
    private String content;
    private Long cash;
}