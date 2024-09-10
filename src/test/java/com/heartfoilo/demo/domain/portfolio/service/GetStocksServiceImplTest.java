package com.heartfoilo.demo.domain.portfolio.service;

import com.heartfoilo.demo.domain.portfolio.entity.TotalAssets;
import com.heartfoilo.demo.domain.portfolio.repository.TotalAssetsRepository;
import com.heartfoilo.demo.domain.stock.entity.Stock;
import com.heartfoilo.demo.domain.stock.repository.StockRepository;

import com.heartfoilo.demo.domain.user.entity.User;
import com.heartfoilo.demo.domain.webSocket.dto.StockSocketInfoDto;
import com.heartfoilo.demo.util.RedisUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetStocksServiceImplTest {

    @InjectMocks
    private GetStocksServiceImpl getStocksService;

    @Mock
    private RedisUtil redisUtil;

    @Mock
    private TotalAssetsRepository totalAssetsRepository;

    @Mock
    private StockRepository stockRepository;

    @Test
    void 자산구성조회_성공() {
        //Given
        Long userId = 1L;
        Long stockId = 1L; // 주식의 실제 ID를 명확하게 설정

        Stock stock = Stock.builder()
                .id(stockId) // stock 객체에 ID 설정
                .name("Test Stock")
                .sector("Technology")
                .symbol("TST")
                .type("3")
                .earningRate(10.5f)
                .englishName("Test Stock EN")
                .build();

        TotalAssets totalAssets = TotalAssets.builder()
                .stock(stock)
                .totalQuantity(10L)
                .build();

        StockSocketInfoDto stockInfo = new StockSocketInfoDto();
        stockInfo.setCurPrice(100);

        // Mocking: 특정 ID로 주식을 찾을 수 있도록 설정
        when(totalAssetsRepository.findByUserId(userId)).thenReturn(Optional.of(List.of(totalAssets)));
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock)); // anyLong() 대신 stockId 사용
        when(redisUtil.getStockInfoTemplate(anyString())).thenReturn(stockInfo);

        //When
        ResponseEntity<Map<String, Object>> response = getStocksService.getStocks(userId);

        //Then
        assertEquals(200, response.getStatusCodeValue());

        List<Map<String, Object>> stockList = (List<Map<String, Object>>) response.getBody().get("stocks");
        assertEquals(1, stockList.size());
        Map<String, Object> stockMap = stockList.get(0);
        assertEquals(stockId, stockMap.get("id")); // 주식의 ID가 맞는지 확인
        assertEquals("Test Stock", stockMap.get("stockName"));
        assertEquals(1000L, stockMap.get("evalPrice")); // 100 * 10 = 1000
        assertEquals("Technology", stockMap.get("sector"));

        // Mock 호출 검증
        verify(totalAssetsRepository, times(1)).findByUserId(userId);
        verify(stockRepository, times(1)).findById(stockId); // stockId로 호출했는지 검증
        verify(redisUtil, times(1)).getStockInfoTemplate(anyString());
    }

}
