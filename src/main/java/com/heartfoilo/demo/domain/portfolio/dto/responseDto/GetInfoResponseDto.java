package com.heartfoilo.demo.domain.portfolio.dto.responseDto;

import com.heartfoilo.demo.domain.user.entity.User;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class GetInfoResponseDto {
    private String name;
    private String email;
    private String nickname;
    private String profile;
    private long cash;
    private long donate;
    private long id;
    private String stockName;
    private double percentage;
    private long totalPurchase;
    private long totalAmount;
    private long totalValue;
    private double profitRate;


}
