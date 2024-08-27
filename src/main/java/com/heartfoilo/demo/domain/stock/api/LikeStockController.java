package com.heartfoilo.demo.domain.stock.api;

import com.heartfoilo.demo.domain.stock.dto.responseDto.LikeStockResponseDto;
import com.heartfoilo.demo.domain.stock.service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock/favorites")
@RequiredArgsConstructor
public class LikeStockController {

    private final LikeService likeService;


    @PostMapping("/{stockId}")
    public ResponseEntity<Void> addFavorite(@PathVariable("stockId") Long stockId, HttpServletRequest request) {
        // Interceptor에서 설정한 userId 속성을 가져옴
        String userId = (String) request.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(401).build(); // Unauthorized 처리
        }

        likeService.addFavorite(Long.valueOf(userId), stockId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<LikeStockResponseDto>> getFavorite(HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).build(); // Unauthorized 처리
        }
        List<LikeStockResponseDto> favorites = likeService.getFavorites(Long.valueOf(userId));
        return ResponseEntity.ok(favorites);
    }

    @DeleteMapping("/{stockId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable("stockId") Long stockId, HttpServletRequest request) {
        String userId = (String) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).build(); // Unauthorized 처리
        }
        likeService.removeFavorite(Long.valueOf(userId), stockId);
        return ResponseEntity.ok().build();
    }
}
