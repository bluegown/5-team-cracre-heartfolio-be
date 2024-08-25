package com.heartfoilo.demo.login.controller;

import com.heartfoilo.demo.login.dto.KakaoUserInfoResponseDto;
import com.heartfoilo.demo.login.dto.LoginResponse;
import com.heartfoilo.demo.login.service.KakaoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<?> callback(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        try {
            // 인가 코드를 사용하여 액세스 토큰을 가져옴
            String accessToken = kakaoService.getAccessTokenFromKakao(code);

            // 액세스 토큰으로 사용자 정보 가져오기
            KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

            // 사용자 정보로 로그인 처리 및 JWT 토큰 생성
            LoginResponse kakaoUserResponse = kakaoService.kakaoUserLogin(userInfo);

            // JWT 토큰을 Authorization 헤더에 추가
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + kakaoUserResponse);

            // JWT 토큰을 포함한 헤더와 함께 빈 본문으로 응답 반환
            return new ResponseEntity<>(null, headers, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found");
        }
    }

} // code를 이용해서 accessToken get해오기
// 프론트엔드에서 인가코드를 전송받는다