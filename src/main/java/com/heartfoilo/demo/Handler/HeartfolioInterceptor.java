package com.heartfoilo.demo.Handler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@NoArgsConstructor
@Component
public class HeartfolioInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 요청 전에 수행할 로직
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰이 유효하지 않습니다.");
            return false; // 토큰 존재하지 않을시 요청에 대한 응답이 false가 나온다
        }
        token = token.substring(7);

        try {
            // JWT 토큰을 파싱하여 클레임(Claims) 추출
            Claims claims = Jwts.parser()
                    .setSigningKey("${spring.custom.jwt.secretkey}".getBytes())  // secretKey를 사용해 서명 검증
                    .parseClaimsJws(token)
                    .getBody();

            // 클레임에서 userId 추출
            String userId = claims.get("id", String.class);

            // 추출한 userId를 요청의 속성(attribute)으로 저장
            request.setAttribute("userId", userId);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰이 유효하지 않습니다.");
            return false;
        }

        return true; // true를 반환하면 다음으로 진행, false면 진행 중단
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 컨트롤러에서 처리 후, 뷰가 렌더링되기 전에 수행할 로직
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 뷰가 렌더링된 후에 수행할 로직
    }}
