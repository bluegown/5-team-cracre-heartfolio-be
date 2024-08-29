package com.heartfoilo.demo.domain.portfolio.api;

import com.heartfoilo.demo.domain.portfolio.service.GetAssetsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/portfolio")
public class GetAssetsController {
    @Autowired
    private GetAssetsServiceImpl getAssetsServiceImpl;
    @GetMapping // 보유 자산 조회 API
    public ResponseEntity<Map<String,Object>> getAssets(HttpServletRequest request){
        String userStrId = (String) request.getAttribute("userId");
        System.out.println(userStrId);
        if (userStrId == null) {
            // 비로그인 사용자 처리
            return ResponseEntity.ok(Collections.emptyMap()); // 빈 Map 반환
        }
        return getAssetsServiceImpl.getAssets(Long.valueOf(userStrId));
    }
}
