package com.heartfoilo.demo.domain.invest.service;

import com.heartfoilo.demo.domain.invest.dto.requestDto.InvestRequestDto;
import com.heartfoilo.demo.domain.invest.entity.Orders;
import com.heartfoilo.demo.domain.invest.repository.InvestRepository;
import com.heartfoilo.demo.domain.portfolio.entity.Account;
import com.heartfoilo.demo.domain.portfolio.entity.TotalAssets;
import com.heartfoilo.demo.domain.portfolio.repository.PortfolioRepository;
import com.heartfoilo.demo.domain.portfolio.repository.TotalAssetsRepository;
import com.heartfoilo.demo.domain.stock.entity.Stock;
import com.heartfoilo.demo.domain.stock.repository.StockRepository;
import com.heartfoilo.demo.domain.user.entity.User;
import com.heartfoilo.demo.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InvestServiceImpl {

    @Autowired
    private TotalAssetsRepository totalAssetsRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private InvestRepository investRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private UserRepository userRepository;
    public Orders createOrder(Long userId, String orderCategory, Long nowQuantity, Long nowAvgPrice,Long stockId) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + stockId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setOrderCategory(orderCategory);
        orders.setOrderDate(LocalDateTime.now());
        orders.setOrderAmount(nowQuantity);
        orders.setOrderPrice(nowAvgPrice);
        orders.setTotalAmount(nowQuantity * nowAvgPrice);
        orders.setStock(stock);
        orders.setUser(user);
        return orders;
    }
    public TotalAssets order(InvestRequestDto getInfoRequestDto){
        Long stockId = getInfoRequestDto.getStockId();
        Long quantity = getInfoRequestDto.getQuantity();
        long price = getInfoRequestDto.getPrice();

        TotalAssets totalAssets = totalAssetsRepository.findByStockId(stockId);
        Long nowQuantity = totalAssets.getTotalQuantity();
        Long nowAvgPrice = totalAssets.getPurchaseAvgPrice(); // 현재 평단가

        nowAvgPrice = ((nowQuantity * nowAvgPrice) + (quantity * price)) / (nowQuantity + quantity);
        nowQuantity = nowQuantity + quantity ; // 산만큼 더해주고
        totalAssets.setTotalQuantity(nowQuantity); // quantity 변경
        totalAssets.setPurchaseAvgPrice(nowAvgPrice); // avgprice 변경


        // orders 엔티티에 있는 내역 업데이트.
        Orders orders = createOrder(1L, "buy", quantity, price,1L);
        // stock_id는 auto_increment로 설정 필요해보임
        investRepository.save(orders);

        // Account 엔티티 업데이트
        Optional<Account> accountOptional = portfolioRepository.findById(1L); // 임시 코드
        Account account = accountOptional.orElseThrow(() -> new RuntimeException("Account not found with id: 1"));
        Long cash = account.getCash(); // 현재 잔액 조회
        Long totalPurchase = account.getTotalPurchase();
        if (cash >= quantity * price) {
            account.setCash(cash - (quantity * price));
            account.setTotalPurchase(totalPurchase + (quantity * price));
        } // Cash의 보유량을 빼고 , total_purchase를 매수한 수량 * 평단가만큼 더함
        else{
            throw new RuntimeException("잔액이 부족합니다");
        } // Account 업데이트
        portfolioRepository.save(account); // 새로운 내역들로 업데이트 시켜준다.

        // 4. 변경된 엔티티를 저장합니다.
        return totalAssetsRepository.save(totalAssets);
    }

    public TotalAssets sell(InvestRequestDto getInfoRequestDto){
        Long stockId = getInfoRequestDto.getStockId();
        Long quantity = getInfoRequestDto.getQuantity(); // 요청한 수량
        long price = getInfoRequestDto.getPrice();

        TotalAssets totalAssets = totalAssetsRepository.findByStockId(stockId);

        Long nowQuantity = totalAssets.getTotalQuantity();
        Long nowAvgPrice = totalAssets.getPurchaseAvgPrice(); // 현재 평단가
        nowQuantity = nowQuantity - quantity ; // 판만큼 빼주고 , 판매시 평단가는 그대로임
        Orders orders = createOrder( 1L, "sell", quantity, price,1L);
        if (nowQuantity < 0){
            throw new IllegalArgumentException("판매할 수량이 보유 수량보다 많습니다.");
        }
        investRepository.save(orders);

        Optional<Account> accountOptional = portfolioRepository.findById(1L); // 임시 코드
        Account account = accountOptional.orElseThrow(() -> new RuntimeException("Account not found with id: 1"));
        Long cash = account.getCash(); // 현재 잔액 조회
        Long totalPurchase = account.getTotalPurchase();

        account.setCash(cash + (quantity * price));
        account.setTotalPurchase(totalPurchase - (quantity * price));

        portfolioRepository.save(account);
        totalAssets.setTotalQuantity(nowQuantity); // quantity 변경


        return totalAssetsRepository.save(totalAssets); // 항목들 변경해주고 save
    } // 만약 총수량이 0이된다면 레포지토리에서 삭제해야 하나?
}
