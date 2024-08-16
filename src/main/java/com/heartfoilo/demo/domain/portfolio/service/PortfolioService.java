package com.heartfoilo.demo.domain.portfolio.service;

import com.heartfoilo.demo.domain.invest.entity.Order;
import com.heartfoilo.demo.domain.portfolio.dto.responseDto.GetInfoResponseDto;
import com.heartfoilo.demo.domain.portfolio.entity.TotalAssets;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PortfolioService {

    public GetInfoResponseDto getAssets(long userId);
    public Map<String,Object> getStocks(long userId);

    public List<Order> getInvestInfo();


}
