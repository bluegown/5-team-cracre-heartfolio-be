package com.heartfoilo.demo.domain.portfolio.service;

import org.springframework.stereotype.Service;
import com.heartfoilo.demo.domain.invest.dto.responseDto.GetInfoResponseDto;
import java.util.List;
import java.util.Map;

@Service
public interface PortfolioService {

    public Map<String,Object> getAssets(long userId);
    public Map<String,Object> getStocks(long userId);

    public List<GetInfoResponseDto> getInvestInfo();


}
