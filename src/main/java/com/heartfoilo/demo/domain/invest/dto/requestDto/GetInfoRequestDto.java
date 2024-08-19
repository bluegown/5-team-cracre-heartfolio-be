package com.heartfoilo.demo.domain.invest.dto.requestDto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class GetInfoRequestDto {
    private long quantity;
    private long price;
    private long stockId;
}
