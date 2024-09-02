package com.heartfoilo.demo.login.controller;

import com.heartfoilo.demo.login.Entity.RefreshToken;
import com.heartfoilo.demo.login.Generator.JwtTokenProvider;
import com.heartfoilo.demo.login.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshTokenStr = request.get("refreshToken");

        RefreshToken refreshToken = refreshTokenService.findByToken(refreshTokenStr)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        // 만료 여부 확인
        refreshTokenService.verifyExpiration(refreshToken);

        // 새로운 access token 발급
        String newAccessToken = jwtTokenProvider.accessTokenGenerate(refreshToken.getUser().getId().toString());

        return ResponseEntity.ok(new HashMap<String, String>() {{
            put("accessToken", newAccessToken);
        }});
    } // TODO: 여기서부터 다시하기 !! 
}
