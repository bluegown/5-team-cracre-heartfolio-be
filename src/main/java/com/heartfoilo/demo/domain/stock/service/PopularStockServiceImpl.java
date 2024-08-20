package com.heartfoilo.demo.domain.stock.service;

import com.heartfoilo.demo.domain.stock.constant.ErrorMessage;
import com.heartfoilo.demo.domain.stock.dto.responseDto.PopularStockResponseDto;
import com.heartfoilo.demo.domain.stock.entity.Stock;
import com.heartfoilo.demo.domain.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PopularStockServiceImpl implements PopularStockService{

    private final StockRepository stockRepository;


    //TODO: 인기종목 기준 -> 실시간 거래량으로 변경해야함 현재는 DB stock_info테이블에서 시가총액 기준으로 들고오는 중 수정 필요
    @Override
    public List<PopularStockResponseDto> getPopularStocks(int limit) {
        //FIXME: 실시간 거래량 연동되면 StockRepository 쿼리문 변경하기
        Pageable pageable = PageRequest.of(0, limit);
        List<Stock> popularStocks=  stockRepository.findTopStocksByTotalMarketPrice(pageable);
        if (popularStocks.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.POPULAR_STOCK_NOT_FOUND);
        }
        return popularStocks.stream()
                .map(stock -> new PopularStockResponseDto(
                        stock.getId(),
                        stock.getName(),
                        stock.getEarningRate()
                )).collect(Collectors.toList());
    }
}
