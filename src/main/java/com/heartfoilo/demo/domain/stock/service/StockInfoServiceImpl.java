package com.heartfoilo.demo.domain.stock.service;

import com.heartfoilo.demo.domain.portfolio.entity.TotalAssets;
import com.heartfoilo.demo.domain.portfolio.repository.TotalAssetsRepository;
import com.heartfoilo.demo.domain.stock.dto.responseDto.GetAmountResponseDto;
import com.heartfoilo.demo.domain.stock.entity.Stock;
import com.heartfoilo.demo.domain.stock.repository.StockRepository;
import com.heartfoilo.demo.global.exception.StockNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StockInfoServiceImpl {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private TotalAssetsRepository totalAssetsRepository;
    public GetAmountResponseDto getInfo(long stockId) {
        // stockId로 Stock을 조회
        Stock stock = stockRepository.findById(stockId);
        if (stock == null) {
            throw new StockNotFoundException("Stock with ID " + stockId + " not found");
        }

        // symbol과 name 값을 가져옴
        String symbol = stock.getType() + ":" + stock.getSymbol();
        String name = stock.getName();

        // stockId로 TotalAssets를 조회
        TotalAssets totalAssets = totalAssetsRepository.findByStockId(stockId);
        if (totalAssets == null) {
            throw new StockNotFoundException("Stock with ID " + stockId + " not found");
        }

        // 총량을 가져옴
        Long quantity = totalAssets.getTotalQuantity();

        // GetAmountResponseDto 객체 생성 후 반환
        return new GetAmountResponseDto(symbol, name, quantity);
    }
}
