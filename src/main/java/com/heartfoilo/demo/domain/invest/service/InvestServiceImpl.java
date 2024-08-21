package com.heartfoilo.demo.domain.invest.service;

import com.heartfoilo.demo.domain.invest.dto.requestDto.InvestRequestDto;
import com.heartfoilo.demo.domain.invest.entity.Order;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Order createOrder(Long userId, String orderCategory, Long nowQuantity, int nowAvgPrice, Long stockId) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock not found with id: " + stockId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Order orders = new Order();
        orders.setUser(user);
        orders.setOrderCategory(orderCategory);
        orders.setOrderDate(LocalDateTime.now());
        orders.setOrderAmount(nowQuantity);
        orders.setOrderPrice(nowAvgPrice);
        orders.setTotalAmount(nowQuantity * nowAvgPrice);
        orders.setStock(stock);
        orders.setUser(user);
        return orders;
    } // order 객체 새로 만들어주는 기능

    public ResponseEntity<?> order(InvestRequestDto getInfoRequestDto){
        Long stockId = getInfoRequestDto.getStockId();
        Long quantity = getInfoRequestDto.getQuantity();
        long price = getInfoRequestDto.getPrice();

        Optional<Stock> optionalStock = stockRepository.findById(stockId);
        Stock stock = optionalStock.get();

        User user = userRepository.findById(1L);

        TotalAssets totalAssets = totalAssetsRepository.findByStockId(stockId);
        if (totalAssets == null) {
            totalAssets = new TotalAssets();
            totalAssets.setStock(stock);
            totalAssets.setUser(user);
            totalAssets.setTotalQuantity(quantity);
            totalAssets.setPurchaseAvgPrice(price);
            totalAssetsRepository.save(totalAssets);
            return ResponseEntity.ok("자산이 성공적으로 업데이트되었습니다.");
        } // 만약 기록이 없는 주식을 매수했다면 , 새로운 객체를 생성해서 set -> 이후 업데이트 완료 return
        Long nowQuantity = totalAssets.getTotalQuantity();
        Long nowAvgPrice = totalAssets.getPurchaseAvgPrice(); // 현재 평단가

        nowAvgPrice = ((nowQuantity * nowAvgPrice) + (quantity * price)) / (nowQuantity + quantity); // 새로운 평단가 갱신
        nowQuantity = nowQuantity + quantity ; // 산만큼 더해준다 . 새로운 quantity 갱신
        totalAssets.setTotalQuantity(nowQuantity); // quantity 변경
        totalAssets.setPurchaseAvgPrice(nowAvgPrice); // avgprice 변경


        // orders 엔티티에 있는 내역 업데이트.
        Order orders = createOrder(1L, "buy", quantity, (int) price,1L);
        // stock_id는 auto_increment로 설정.
        investRepository.save(orders); // 투자 내역또한 저장 완료

        // Account 엔티티 업데이트
        Optional<Account> accountOptional = portfolioRepository.findById(1L); // 임시 코드
        Account account = accountOptional.orElseThrow(() -> new RuntimeException("Account not found with id: 1"));
        // 아이디를 찾는다면 account 객체 사용. 아니라면 RuntimeException 처리
        Long cash = account.getCash(); // 현재 잔액 조회
        Long totalPurchase = account.getTotalPurchase();
        if (cash >= quantity * price) { // 만약 보유한 cash가 매수하려는 금액보다 이상이라면 -> 작업 시행
            account.setCash(cash - (quantity * price)); // 매수이므로 cash는 차감
            account.setTotalPurchase(totalPurchase + (quantity * price)); // 매수한만큼 추가
        } // Cash의 보유량을 빼고 , total_purchase를 매수한 수량 * 평단가만큼 더함
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잔액이 부족합니다.");
        }
        portfolioRepository.save(account); // 새로운 내역들로 업데이트 시켜준다.

        //변경된 엔티티를 저장
        totalAssetsRepository.save(totalAssets);
        return ResponseEntity.ok("buy order successfully processed and total assets updated.");
    }

    public ResponseEntity<?> sell(InvestRequestDto getInfoRequestDto){
        Long stockId = getInfoRequestDto.getStockId();
        Long quantity = getInfoRequestDto.getQuantity(); // 요청한 수량
        long price = getInfoRequestDto.getPrice();

        TotalAssets totalAssets = totalAssetsRepository.findByStockId(stockId);
        if (totalAssets == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 자산을 찾을 수 없습니다.");
        } // 팔려는데 Null이면 그건 문제가 있는거임
        Long nowQuantity = totalAssets.getTotalQuantity();
        Long nowAvgPrice = totalAssets.getPurchaseAvgPrice(); // 현재 평단가
        nowQuantity = nowQuantity - quantity ; // 판만큼 빼주고 , 판매시 평단가는 그대로임
        Order orders = createOrder( 1L, "sell", quantity, (int)price,1L);
        if (nowQuantity < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("판매할 수량이 보유 수량보다 많습니다.");
        }
        investRepository.save(orders);

        Optional<Account> accountOptional = portfolioRepository.findById(1L); // 임시 코드
        Account account = accountOptional.orElseThrow(() -> new RuntimeException("Account not found with id: 1"));
        Long cash = account.getCash(); // 현재 잔액 조회
        Long totalPurchase = account.getTotalPurchase();

        account.setCash(cash + (quantity * price));
        account.setTotalPurchase(totalPurchase - (quantity * price));

        portfolioRepository.save(account);
        if (nowQuantity == 0) {
            totalAssetsRepository.delete(totalAssets);
            return ResponseEntity.ok("자산이 전부 판매되어 삭제되었습니다.");
        } else {
            // total_quantity가 0이 아니면 업데이트 후 저장
            totalAssets.setTotalQuantity(nowQuantity); // quantity 변경
            totalAssetsRepository.save(totalAssets); // 항목들 변경해주고 save
            return ResponseEntity.ok("Sell order successfully processed and total assets updated.");
        }



    } // 만약 총수량이 0이된다면 레포지토리에서 삭제해야 하나?
}
