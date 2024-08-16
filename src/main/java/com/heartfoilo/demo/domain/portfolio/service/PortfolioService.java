package com.heartfoilo.demo.domain.portfolio.service;

import com.heartfoilo.demo.domain.portfolio.dto.responseDto.GetInfoResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface PortfolioService {

    public GetInfoResponseDto getAssets(long userId);
    public GetInfoResponseDto getStocks(long userId);

}
