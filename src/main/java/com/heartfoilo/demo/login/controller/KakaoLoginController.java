package com.heartfoilo.demo.login.controller;

import com.heartfoilo.demo.login.dto.KakaoUserInfoResponseDto;
import com.heartfoilo.demo.login.dto.LoginResponse;
import com.heartfoilo.demo.login.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor

public class KakaoLoginController {

    private final KakaoService kakaoService;

    @GetMapping("/oauth")
    public ResponseEntity<?> callback(@RequestParam("code") String code) throws IOException {

        try{
            // 현재 도메인 확인
            String accessToken = kakaoService.getAccessTokenFromKakao(code);

            KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken); // 토큰 기반으로 유저 정보 가져옴

            LoginResponse KakaoUserResponse = kakaoService.kakaoUserLogin(userInfo);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Item Not Found");
        }
    }

} // code를 이용해서 accessToken get해오기
// 프론트엔드에서 인가코드를 전송받는다