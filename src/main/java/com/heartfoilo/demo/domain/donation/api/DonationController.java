package com.heartfoilo.demo.domain.donation.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donation")
public class DonationController {
    @GetMapping("/user/profile")
    public String getUserProfile(HttpServletRequest request) {
        // 요청 속성에서 userId를 가져옴
        String userId = (String) request.getAttribute("userId");

        // userId를 이용해 사용자 프로필 정보를 반환
        return "User Profile for User ID: " + userId;
    }

}
