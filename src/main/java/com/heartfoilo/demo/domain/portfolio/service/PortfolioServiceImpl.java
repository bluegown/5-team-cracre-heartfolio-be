package com.heartfoilo.demo.domain.portfolio.service;

import com.heartfoilo.demo.domain.invest.entity.Account;
import com.heartfoilo.demo.domain.portfolio.dto.responseDto.GetInfoResponseDto;
import com.heartfoilo.demo.domain.portfolio.repository.PortfolioRepository;
import com.heartfoilo.demo.domain.user.entity.User;
import com.heartfoilo.demo.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heartfoilo.demo.domain.portfolio.dto.*;

import java.util.Optional;

@Service
public class PortfolioServiceImpl implements PortfolioService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;


    @Override
    public GetInfoResponseDto getAssets(long userId){ // 보유 자산 조회 API

        User user = userRepository.findByUserId(userId);
        Optional<Account> accountOpt = portfolioRepository.findById(userId);

        Account account = accountOpt.get(); // 꺼내오고

        long cash = account.getCash();
        long totalPurchase = account.getTotalPurchase();
        GetInfoResponseDto responseDto = new GetInfoResponseDto(user,cash,totalPurchase);
// 요기 이제 소켓 데이터 들어가면 됨
        return responseDto;
    }

    @Override
    public GetInfoResponseDto getStocks(long userId){ // 자산 구성 조회 API

        return null;
    }


}
