package com.heartfoilo.demo.domain.stock.service;

import com.heartfoilo.demo.domain.portfolio.entity.TotalAssets;
import com.heartfoilo.demo.domain.portfolio.repository.TotalAssetsRepository;
import com.heartfoilo.demo.domain.stock.dto.responseDto.GetAmountResponseDto;
import com.heartfoilo.demo.domain.stock.entity.Stock;
import com.heartfoilo.demo.domain.stock.entity.Like;
import com.heartfoilo.demo.domain.stock.repository.LikeRepository;
import com.heartfoilo.demo.domain.stock.repository.StockRepository;
import com.heartfoilo.demo.global.exception.StockNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StockInfoServiceImpl {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private TotalAssetsRepository totalAssetsRepository;
    @Autowired
    private LikeRepository likeRepository;
    public GetAmountResponseDto getInfo(long stockId) {
        // stockId로 Stock을 조회
        Stock stock = stockRepository.findById(stockId);
        if (stock == null) {
            throw new StockNotFoundException("Stock with ID " + stockId + " not found");
        }

        // symbol과 name 값을 가져옴
        String symbol = stock.getSymbol();
        String name = stock.getName();

        // stockId로 TotalAssets를 조회
        TotalAssets totalAssets = totalAssetsRepository.findByStockId(stockId);
        if (totalAssets == null) {
            throw new StockNotFoundException("Stock with ID " + stockId + " not found");
        }

        // 총량을 가져옴
        Long quantity = totalAssets.getTotalQuantity();
        if(quantity == null){
            quantity = 0L;
        }
        Optional<Like> like = likeRepository.findByUserIdAndStockId(stockId,1L);
        // TODO: userId 수정
        boolean isLikePresent = like.isPresent();



        // GetAmountResponseDto 객체 생성 후 반환
        return new GetAmountResponseDto(symbol, name, quantity,isLikePresent);
    }
}
