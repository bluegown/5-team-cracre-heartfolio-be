package com.heartfoilo.demo.login.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {
    private Long id;
    private String nickname;
    private KakaoTokenResponseDto token;

    public LoginResponse(Long id, String nickname, KakaoTokenResponseDto token) {
        this.id = id;
        this.nickname = nickname;

        this.token = token;
    }
}