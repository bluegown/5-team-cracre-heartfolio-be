package com.heartfoilo.demo.domain.stock.api;

import com.heartfoilo.demo.domain.stock.service.LikeService;
import com.heartfoilo.demo.domain.stock.service.UserService;
import com.heartfoilo.demo.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    private final LikeService likeService;
    private final UserService userService;


    @PostMapping("favorites/{stock_id}")
    public ResponseEntity<Map<String, String>> addFavorite(@PathVariable("stock_id") Long stockId) {
        // TODO: 사용자 인증 추가시 변경 - OAuth2 인증 로직을 추가해야 합니다.
        Long userId = 1L; // 임시로 하드코딩된 사용자 ID, 추후 OAuth2 인증 후 userId를 가져오는 로직으로 변경

        Map<String, String> response = new HashMap<>();

        likeService.addFavorite(userId, stockId);
        response.put("message", "Stock added to favorites successfully.");
        return ResponseEntity.ok(response);
    }
}
