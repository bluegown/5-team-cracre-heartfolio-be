package com.heartfoilo.demo.domain.portfolio.api;


import com.heartfoilo.demo.domain.invest.entity.Order;
import com.heartfoilo.demo.domain.portfolio.dto.responseDto.GetInfoResponseDto;
import com.heartfoilo.demo.domain.portfolio.entity.TotalAssets;
import com.heartfoilo.demo.domain.portfolio.service.PortfolioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioServiceImpl portfolioServiceImpl;


    @GetMapping("/{userId}") // 보유 자산 조회 API
    public GetInfoResponseDto getAssets(@PathVariable ("userId") long userId){

        return portfolioServiceImpl.getAssets(userId);
    }
    @GetMapping("/{userId}/stock") // 자산 구성 조회 API
    public Map<String,Object> getStocks(@PathVariable ("userId") long userId){

        return portfolioServiceImpl.getStocks(userId);

    }

    @GetMapping("/investInfo")
    public List<Order> getInvestInfo(){
        return portfolioServiceImpl.getInvestInfo();
    }




}
