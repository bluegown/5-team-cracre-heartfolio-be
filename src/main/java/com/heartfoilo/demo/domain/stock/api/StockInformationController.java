package com.heartfoilo.demo.domain.stock.api;


import com.heartfoilo.demo.domain.stock.dto.responseDto.GetAmountResponseDto;
import com.heartfoilo.demo.domain.stock.service.StockInfoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/stock")
public class StockInformationController {

    @Autowired
    private StockInfoServiceImpl stockInfoServiceImpl;
    @GetMapping("/{stock_id}") // 보유 자산 조회 API
    public GetAmountResponseDto getStockInfo(@PathVariable("stock_id") long stockId){


        return stockInfoServiceImpl.getInfo(stockId);
    }
}
