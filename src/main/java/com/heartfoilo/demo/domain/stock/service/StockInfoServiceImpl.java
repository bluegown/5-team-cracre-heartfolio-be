package com.heartfoilo.demo.domain.stock.service;

import com.heartfoilo.demo.domain.portfolio.entity.TotalAssets;
import com.heartfoilo.demo.domain.portfolio.repository.TotalAssetsRepository;
import com.heartfoilo.demo.domain.stock.dto.responseDto.GetAmountResponseDto;
import com.heartfoilo.demo.domain.stock.entity.Stock;
import com.heartfoilo.demo.domain.stock.entity.Like;
import com.heartfoilo.demo.domain.stock.repository.LikeRepository;
import com.heartfoilo.demo.domain.stock.repository.StockRepository;
import com.heartfoilo.demo.domain.webSocket.dto.StockSocketInfoDto;
import com.heartfoilo.demo.global.exception.StockNotFoundException;
import com.heartfoilo.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StockInfoServiceImpl implements StockInfoService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private TotalAssetsRepository totalAssetsRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private RedisUtil redisUtil;

    public GetAmountResponseDto getInfo(long stockId) {
        // stockId로 Stock을 조회
        Stock stock = stockRepository.findById(stockId);
        if (stock == null) {
            throw new StockNotFoundException("Stock with ID " + stockId + " not found");
        }

        // symbol과 name 값을 가져옴
        String symbol = stock.getSymbol();
        String name = stock.getEnglishName();

        // stockId로 TotalAssets를 조회
        TotalAssets totalAssets = totalAssetsRepository.findByStockId(stockId);
        Long quantity;
        if (totalAssets == null) {
            quantity = 0L;
        } else {
            quantity = totalAssets.getTotalQuantity();
        }

         // 예외처리 !!
        Optional<Like> like = likeRepository.findByUserIdAndStockId(1L,stockId);
        // TODO: userId 수정
        boolean isLikePresent = like.isPresent();

        // TODO : 현재가 추가
        int curPrice= 0;
        if (redisUtil.hasKeyStockInfo(stock.getSymbol())) {
            StockSocketInfoDto stockInfo = redisUtil.getStockInfoTemplate(stock.getSymbol());
            curPrice = stockInfo.getCurPrice();
        }

        // GetAmountResponseDto 객체 생성 후 반환
        return new GetAmountResponseDto(symbol, name, quantity, curPrice, isLikePresent);
    }
}
