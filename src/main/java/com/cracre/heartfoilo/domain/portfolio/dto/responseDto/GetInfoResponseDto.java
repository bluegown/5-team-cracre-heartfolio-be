package com.cracre.heartfoilo.domain.portfolio.dto.responseDto;

import com.cracre.heartfoilo.domain.user.entity.User;
import lombok.*;

@Data

@Getter
public class GetInfoResponseDto {
    private final long cash;
    private final long totalPurchase;


    @Builder
    public GetInfoResponseDto(User user, long cash, long totalPurchase){

        this.cash = cash;
        this.totalPurchase = totalPurchase;
    }
}
