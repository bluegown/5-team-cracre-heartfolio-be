package com.cracre.heartfoilo.domain.portfolio.service;

import com.cracre.heartfoilo.domain.portfolio.dto.responseDto.GetInfoResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface PortfolioService {

    public GetInfoResponseDto getAssets(long userId);
    public GetInfoResponseDto getStocks(long userId);

}
