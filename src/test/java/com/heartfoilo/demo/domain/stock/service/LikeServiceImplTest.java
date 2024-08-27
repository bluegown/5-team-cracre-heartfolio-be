package com.heartfoilo.demo.domain.stock.service;

import com.heartfoilo.demo.domain.stock.constant.ErrorMessage;
import com.heartfoilo.demo.domain.stock.entity.Stock;
import com.heartfoilo.demo.domain.stock.repository.LikeRepository;
import com.heartfoilo.demo.domain.stock.repository.StockRepository;
import com.heartfoilo.demo.domain.user.entity.User;
import com.heartfoilo.demo.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LikeServiceImplTest {

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private LikeServiceImpl likeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addFavorite() {
//        Long userId = 1L;
//        Long stockId = 1L;
//        Stock stock = new Stock();
//
//        User user = new User();
//
//        //when(stockRepository.findById(stockId)).thenReturn(Optional.empty());
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
//                likeService.addFavorite(userId, stockId));
//
//        assertEquals(ErrorMessage.STOCK_NOT_FOUND, exception.getMessage());
    }

    @Test
    void getFavorites() {
    }

    @Test
    void removeFavorite() {
    }
}