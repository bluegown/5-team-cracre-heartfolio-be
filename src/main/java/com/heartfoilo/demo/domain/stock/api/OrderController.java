package com.heartfoilo.demo.domain.stock.api;

import com.heartfoilo.demo.domain.stock.dto.responseDto.GetAmountResponseDto;
import com.heartfoilo.demo.domain.stock.dto.responseDto.OrderHistoryResponseDto;
import com.heartfoilo.demo.domain.stock.dto.responseDto.StockSearchResponseDto;
import com.heartfoilo.demo.domain.stock.service.OrderService;
import com.heartfoilo.demo.domain.stock.service.StockInfoService;
import com.heartfoilo.demo.domain.stock.service.StockInfoServiceImpl;
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

    @GetMapping("/{stockId}/order")
    public ResponseEntity<List<OrderHistoryResponseDto>> getOrderHistory(@PathVariable("stockId") Long stockId) {
        // TODO: 사용자 인증 추가시 변경 - OAuth2 인증 로직을 추가해야 합니다.
        Long userId = 1L; // 임시로 하드코딩된 사용자 ID, 추후 OAuth2 인증 후 userId를 가져오는 로직으로 변경
        // FIXME: stockId 필요한지 검토필요 필요없다면 API 수정해야할듯 /api/order
        List<OrderHistoryResponseDto> history = orderService.getOrderHistory(userId, stockId);
        return ResponseEntity.ok(history);
    }

    @GetMapping("/search")
    public ResponseEntity<List<StockSearchResponseDto>> searchStocks(@RequestParam("keyword") String keyword) {
        List<StockSearchResponseDto> results = orderService.getStockSearch(keyword);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{stock_id}") // 보유 자산 조회 API
    public GetAmountResponseDto getStockInfo(@PathVariable("stock_id") long stockId){
        return stockInfoService.getInfo(stockId);
    }
}
