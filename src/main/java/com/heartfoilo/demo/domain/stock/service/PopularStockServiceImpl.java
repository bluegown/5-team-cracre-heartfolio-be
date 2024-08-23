package com.heartfoilo.demo.domain.stock.service;

import com.heartfoilo.demo.domain.stock.constant.ErrorMessage;
import com.heartfoilo.demo.domain.stock.dto.responseDto.PopularStockResponseDto;
import com.heartfoilo.demo.domain.stock.entity.Stock;
import com.heartfoilo.demo.domain.stock.repository.StockRepository;
import com.heartfoilo.demo.global.exception.PopularStockNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PopularStockServiceImpl implements PopularStockService{

    private final StockRepository stockRepository;


    @Override
    public List<PopularStockResponseDto> getPopularStocks(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Stock> popularStocks=  stockRepository.findAllByOrderByEarningRateDesc(pageable);

        AtomicInteger rankCounter = new AtomicInteger(1);

        return popularStocks.stream()
                .map(stock -> new PopularStockResponseDto(
                        stock.getId(),
                        rankCounter.getAndIncrement(), //순위
                        stock.getName(),
                        12000, //현재가
                        1, //전일대비 수익률
                        stock.getEarningRate()
                )).collect(Collectors.toList());
    }
}
