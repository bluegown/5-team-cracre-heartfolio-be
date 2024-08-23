package com.heartfoilo.demo.login.Generator;

import com.heartfoilo.demo.login.dto.KakaoTokenResponseDto;

import java.util.Date;

public class AuthTokensGenerator {
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60;	//1시간
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일로 설정

     private final JwtTokenProvider jwtTokenProvider;

    public AuthTokensGenerator(JwtTokenProvider jwtTokenProvicer) {
        this.jwtTokenProvider = jwtTokenProvicer;
    }

    public KakaoTokenResponseDto generate(String id){
        long now = (new Date().getTime());
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = jwtTokenProvider.accessTokenGenerate(id, accessTokenExpiredAt);
        String refreshToken = jwtTokenProvider.refreshTokenGenerate(refreshTokenExpiredAt);

        return KakaoTokenResponseDto.AuthTokens(accessToken, refreshToken, BEARER_TYPE, ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }
}
