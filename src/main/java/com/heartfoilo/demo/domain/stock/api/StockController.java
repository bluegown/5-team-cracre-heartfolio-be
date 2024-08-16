package com.heartfoilo.demo.domain.stock.api;

import com.heartfoilo.demo.domain.stock.dto.responseDto.FavoriteStockResponseDto;
import com.heartfoilo.demo.domain.stock.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    private final LikeService likeService;


    @PostMapping("/favorites/{stockId}")
    public ResponseEntity<Void> addFavorite(@PathVariable("stockId") Long stockId) {
        // TODO: 사용자 인증 추가시 변경 - OAuth2 인증 로직을 추가해야 합니다.
        Long userId = 1L; // 임시로 하드코딩된 사용자 ID, 추후 OAuth2 인증 후 userId를 가져오는 로직으로 변경

        likeService.addFavorite(userId, stockId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<FavoriteStockResponseDto>> getFavorite() {
        // TODO: 사용자 인증 추가시 변경 - OAuth2 인증 로직을 추가해야 합니다.
        Long userId = 1L; // 임시로 하드코딩된 사용자 ID, 추후 OAuth2 인증 후 userId를 가져오는 로직으로 변경

        List<FavoriteStockResponseDto> favorites = likeService.getFavorites(userId);
        return ResponseEntity.ok(favorites);
    }

    @DeleteMapping("/favorites/{stockId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable("stockId") Long stockId) {
        // TODO: 사용자 인증 추가시 변경 - OAuth2 인증 로직을 추가해야 합니다.
        Long userId = 1L; // 임시로 하드코딩된 사용자 ID, 추후 OAuth2 인증 후 userId를 가져오는 로직으로 변경

        likeService.removeFavorite(userId, stockId);
        return ResponseEntity.ok().build();
    }
}
