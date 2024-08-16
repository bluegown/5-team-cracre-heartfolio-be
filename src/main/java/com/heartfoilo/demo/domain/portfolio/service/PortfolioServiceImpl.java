package com.heartfoilo.demo.domain.portfolio.service;

import com.heartfoilo.demo.domain.invest.entity.Order;
import com.heartfoilo.demo.domain.invest.repository.InvestRepository;
import com.heartfoilo.demo.domain.portfolio.dto.responseDto.GetInfoResponseDto;
import com.heartfoilo.demo.domain.portfolio.entity.TotalAssets;
import com.heartfoilo.demo.domain.portfolio.repository.PortfolioRepository;
import com.heartfoilo.demo.domain.portfolio.repository.TotalAssetsRepository;
import com.heartfoilo.demo.domain.stock.entity.Stock;
import com.heartfoilo.demo.domain.user.entity.User;
import com.heartfoilo.demo.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heartfoilo.demo.domain.stock.repository.StockRepository;
import com.heartfoilo.demo.domain.portfolio.entity.Account;

import java.util.*;

@Service
public class PortfolioServiceImpl implements PortfolioService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private TotalAssetsRepository totalAssetsRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private InvestRepository investRepository;
    @Override
    public GetInfoResponseDto getAssets(long userId) { // 보유 자산 조회 API

        User user = userRepository.findById(userId);
        Optional<Account> accountOpt = portfolioRepository.findById(userId);

        Account account = accountOpt.get(); // 꺼내오고

        long cash = account.getCash();
        long totalPurchase = account.getTotalPurchase();
        GetInfoResponseDto responseDto = new GetInfoResponseDto();
        responseDto.setCash(cash);
        responseDto.setTotalPurchase(totalPurchase);
        responseDto.setTotalAmount(100000000); // 웹소켓 변동값
        responseDto.setTotalValue(100000000); // 웹소켓 변동값
        responseDto.setProfitRate(-10.4); // 웹소켓 변동값
// 요기 이제 소켓 데이터 들어가면 됨
        return responseDto;
    }


    @Override
    public Map<String,Object> getStocks(long userId) {
        List<TotalAssets> allAssets = totalAssetsRepository.findAll();
        // 여기서 stockId를 가져오고 , 이후에 stock에서 stockName을 찾아온다.
        TotalAssets[] assetsArray = allAssets.toArray(new TotalAssets[0]);

        List<Map<String, Object>> stockList = new ArrayList<>();
        // 3. 배열의 각 항목에 접근하여 필요한 값 가져오기
        for (TotalAssets asset : assetsArray) {
            Long stockId = asset.getStockId();
            Stock findStock = stockRepository.findByStockId(stockId);
            String stockName = findStock.getName();
            double percentage = 10.4; // 실시간 소켓 변동값
            Map<String, Object> stockMap = new HashMap<>();
            stockMap.put("id", stockId);
            stockMap.put("stock_name", stockName);
            stockMap.put("percentage", percentage);

            // 리스트에 추가
            stockList.add(stockMap);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("data", "stocks");
        response.put("stocks", stockList);

        return response;


    }

    @Override
    public List<Order> getInvestInfo(){
        List<Order> order = investRepository.findAll();

        return order;
    }
}
