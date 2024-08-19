package com.heartfoilo.demo.domain.invest.service;

import com.heartfoilo.demo.domain.invest.dto.requestDto.GetInfoRequestDto;
import com.heartfoilo.demo.domain.portfolio.entity.TotalAssets;
import com.heartfoilo.demo.domain.portfolio.repository.TotalAssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestServiceImpl {

    @Autowired
    private TotalAssetsRepository totalAssetsRepository;

    public TotalAssets order(GetInfoRequestDto getInfoRequestDto){
        Long stockId = getInfoRequestDto.getStockId();
        Long quantity = getInfoRequestDto.getQuantity();
        long price = getInfoRequestDto.getPrice();

        TotalAssets totalAssets = totalAssetsRepository.findByStockId(stockId);

        Long nowQuantity = totalAssets.getTotalQuantity();
        Long nowAvgPrice = totalAssets.getPurchaseAvgPrice(); // 현재 평단가
        nowQuantity = nowQuantity + quantity ; // 산만큼 더해주고
        nowAvgPrice = ((nowQuantity * nowAvgPrice) + (quantity * price)) / nowQuantity;
        totalAssets.setTotalQuantity(nowQuantity); // quantity 변경
        totalAssets.setPurchaseAvgPrice(nowAvgPrice); // avgprice 변경

        // 4. 변경된 엔티티를 저장합니다.
        return totalAssetsRepository.save(totalAssets);
    }

    public TotalAssets sell(GetInfoRequestDto getInfoRequestDto){
        Long stockId = getInfoRequestDto.getStockId();
        Long quantity = getInfoRequestDto.getQuantity(); // 요청한 수량
        long price = getInfoRequestDto.getPrice();

        TotalAssets totalAssets = totalAssetsRepository.findByStockId(stockId);

        Long nowQuantity = totalAssets.getTotalQuantity();
        Long nowAvgPrice = totalAssets.getPurchaseAvgPrice(); // 현재 평단가
        nowQuantity = nowQuantity - quantity ; // 판만큼 빼주고
        nowAvgPrice = ((nowQuantity * nowAvgPrice) - (quantity * price)) / nowQuantity;

        totalAssets.setTotalQuantity(nowQuantity); // quantity 변경
        totalAssets.setPurchaseAvgPrice(nowAvgPrice); // avgprice 변경

        return totalAssetsRepository.save(totalAssets); // 항목들 변경해주고 save
    } // 만약 총수량이 0이된다면 레포지토리에서 삭제해야 하나?
}
