package com.heartfoilo.demo.domain.donation.api;

import com.heartfoilo.demo.domain.donation.entity.Donation;
import com.heartfoilo.demo.domain.donation.service.DonationService;
import com.heartfoilo.demo.domain.user.entity.User;
import com.heartfoilo.demo.domain.user.repository.UserRepository;
import com.heartfoilo.demo.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/donation")
public class DonationController {

    // private final UserRepository userRepository;
    private final DonationService donationService;

    @GetMapping("/user/profile")
    public String getUserProfile(HttpServletRequest request) {
        // 요청 속성에서 userId를 가져옴
//        String userId = (String) request.getAttribute("userId");
        String userId = "3674718789"; // TODO: 하드코딩 해제 필요
        // userId를 이용해 사용자 프로필 정보를 반환
        return "User Profile for User ID: " + userId;
    }





}
