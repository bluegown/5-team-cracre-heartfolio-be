package com.heartfoilo.demo.domain.stock.service;

import com.heartfoilo.demo.domain.stock.constant.ErrorMessage;
import com.heartfoilo.demo.domain.stock.dto.responseDto.LikeStockResponseDto;
import com.heartfoilo.demo.domain.stock.entity.Like;
import com.heartfoilo.demo.domain.stock.entity.Stock;
import com.heartfoilo.demo.domain.stock.repository.LikeRepository;
import com.heartfoilo.demo.domain.stock.repository.StockRepository;
import com.heartfoilo.demo.domain.user.entity.User;
import com.heartfoilo.demo.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.STOCK_NOT_FOUND));

        // 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.USER_NOT_FOUND));


        // 새로운 Like 엔티티 생성 및 저장
        Like like = Like.builder()
                .user(user)
                .stock(stock)
                .build();

        return likeRepository.save(like);
    }

    @Override
    public List<LikeStockResponseDto> getFavorites(Long userId) {
        List<Like> likes = likeRepository.findByUserId(userId);
        if (likes.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.FAVORITE_STOCK_NOT_FOUND);
        }
        return likes.stream()
                .map(like -> new LikeStockResponseDto(
                        like.getStock().getId(),
                        like.getStock().getCode(),
                        like.getStock().getName(),
                        //FIXME: 웹 소켓 연동되면 값 변경하기
                        1,
                        12.0f
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void removeFavorite(Long userId, Long stockId) {
        Like like = likeRepository.findByUserIdAndStockId(userId, stockId)
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.DELETE_STOCK_NOT_FOUND));

        // 좋아요 항목을 삭제합니다.
        likeRepository.delete(like);
    }
}
