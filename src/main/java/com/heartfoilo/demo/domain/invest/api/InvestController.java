package com.heartfoilo.demo.domain.invest.api;

import com.heartfoilo.demo.domain.invest.dto.requestDto.InvestRequestDto;
import com.heartfoilo.demo.domain.invest.service.InvestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invest")
public class InvestController {

    @Autowired
    private InvestServiceImpl investServiceImpl;
    @PostMapping("/order")
    public ResponseEntity<?> order(@RequestBody InvestRequestDto getInfoRequestDto){

        return investServiceImpl.order(getInfoRequestDto);
        // entity에 직접적으로 접근 xxx

    }

    @DeleteMapping("/order")
    public ResponseEntity<?> sell(@RequestBody InvestRequestDto getInfoRequestDto){

        return investServiceImpl.sell(getInfoRequestDto);
        // entity에 직접적으로 접근 xxx
    }
}
