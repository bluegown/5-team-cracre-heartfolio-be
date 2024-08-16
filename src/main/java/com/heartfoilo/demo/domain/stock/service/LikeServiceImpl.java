package com.heartfoilo.demo.domain.stock.service;

import com.heartfoilo.demo.domain.stock.entity.Like;
import com.heartfoilo.demo.domain.stock.entity.Stock;
import com.heartfoilo.demo.domain.stock.repository.LikeRepository;
import com.heartfoilo.demo.domain.stock.repository.StockRepository;
import com.heartfoilo.demo.domain.user.entity.User;
import com.heartfoilo.demo.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, UserRepository userRepository, StockRepository stockRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public Like addFavorite(Long userId, Long stockId) {
        // 사용자 조회
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
        User user = userOptional.get();

        // 주식 조회
        Optional<Stock> stockOptional = stockRepository.findById(stockId);
        if (stockOptional.isEmpty()) {
            throw new IllegalArgumentException("주식을 찾을 수 없습니다.");
        }
        Stock stock = stockOptional.get();

        // 이미 존재하는 즐겨찾기인지 확인
        if (likeRepository.existsByUserAndStock(user, stock)) {
            throw new IllegalStateException("이미 즐겨찾기에 추가된 주식입니다.");
        }

        // 새로운 Like 엔티티 생성 및 저장
        Like like = Like.builder()
                .user(user)
                .stock(stock)
                .build();

        return likeRepository.save(like);
    }

    @Override
    public List<Like> getFavorites(Long userId) {
        return List.of();
    }

    @Override
    public void removeFavorite(Long userId, Long stockId) {

    }
}
