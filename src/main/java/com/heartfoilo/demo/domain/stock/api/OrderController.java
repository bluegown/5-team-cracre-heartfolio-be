package com.heartfoilo.demo.domain.stock.api;

import com.heartfoilo.demo.domain.stock.dto.responseDto.GetAmountResponseDto;
import com.heartfoilo.demo.domain.stock.dto.responseDto.OrderHistoryResponseDto;
import com.heartfoilo.demo.domain.stock.dto.responseDto.StockSearchResponseDto;
import com.heartfoilo.demo.domain.stock.service.OrderService;
import com.heartfoilo.demo.domain.stock.service.StockInfoService;
import com.heartfoilo.demo.domain.stock.service.StockInfoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final StockInfoService stockInfoService;

    @GetMapping("/order/history")
    public ResponseEntity<List<OrderHistoryResponseDto>> getOrderHistory(HttpServletRequest request) {
        String userStrId = (String) request.getAttribute("userId");
        if (userStrId == null) {
            // 비로그인 사용자 처리
            return ResponseEntity.status(401).build(); // 기본값 반환
        }
        Long userId = Long.parseLong(userStrId);

        List<OrderHistoryResponseDto> history = orderService.getOrderHistory(userId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/search")
    public ResponseEntity<List<StockSearchResponseDto>> searchStocks(@RequestParam("keyword") String keyword) {
        List<StockSearchResponseDto> results = orderService.getStockSearch(keyword);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/order/{stock_id}") // 보유 자산 조회 API
    public ResponseEntity<GetAmountResponseDto> getStockInfo(@PathVariable("stock_id") long stockId, HttpServletRequest request){
        String userStrId = (String) request.getAttribute("userId");
        if (userStrId == null) {
            // 비로그인 사용자 처리
            return ResponseEntity.status(401).build(); // 기본값 반환
        }
        Long userId = Long.parseLong(userStrId);
        GetAmountResponseDto getAmountResponseDto =  stockInfoService.getInfo(userId, stockId);
        return ResponseEntity.ok(getAmountResponseDto);
    }
}
