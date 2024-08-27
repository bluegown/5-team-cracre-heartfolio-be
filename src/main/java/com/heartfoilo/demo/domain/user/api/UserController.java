package com.heartfoilo.demo.domain.user.api;

import com.heartfoilo.demo.domain.webSocket.dto.StockSocketInfoDto;
import com.heartfoilo.demo.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final RedisUtil redisUtil;
    @GetMapping("/test")
    public void test(){
        redisUtil.setStockInfoTemplate("AAPL", StockSocketInfoDto.builder().symbol("AAPL").curPrice(2263600)
            .openPrice(2200000).highPrice(2400000).lowPrice(2100000).earningValue(11000).earningRate(11.1f).build());
    }

}
