package com.heartfoilo.demo.domain.stock.api;


import com.heartfoilo.demo.domain.stock.dto.responseDto.GetAmountResponseDto;
import com.heartfoilo.demo.domain.stock.service.StockInfoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public GetAmountResponseDto getStockInfo(@PathVariable("stock_id") long stockId, HttpServletRequest request) throws Exception {

        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
           throw new Exception("토큰이 존재하지 않습니다!");
        } // 일단 토큰 먼저 받고
        return stockInfoServiceImpl.getInfo(stockId);
    }
}
