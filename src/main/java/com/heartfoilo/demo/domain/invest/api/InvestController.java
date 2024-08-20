package com.heartfoilo.demo.domain.invest.api;

import com.heartfoilo.demo.domain.invest.dto.requestDto.InvestRequestDto;
import com.heartfoilo.demo.domain.invest.service.InvestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invest")
public class InvestController {

    @Autowired
    private InvestServiceImpl investServiceImpl;
    @PostMapping("/order")
    public String order(@RequestBody InvestRequestDto getInfoRequestDto){

        investServiceImpl.order(getInfoRequestDto);
 // entity에 직접적으로 접근 xxx
        return "Buy order successfully processed and total assets updated!";
    }

    @DeleteMapping("/order")
    public String sell(@RequestBody InvestRequestDto getInfoRequestDto){

        investServiceImpl.sell(getInfoRequestDto);
        // entity에 직접적으로 접근 xxx
        return "sell order successfully processed and total assets updated!";
    }
}
