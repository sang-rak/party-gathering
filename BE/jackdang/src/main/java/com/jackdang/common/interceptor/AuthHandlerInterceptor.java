package com.jackdang.common.interceptor;

import com.jackdang.common.auth.JwtAuthProvider;
import com.jackdang.common.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private final JwtAuthProvider jwtAuthProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  Object handler){

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // preflight로 넘어온 options는 통과
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        if (StringUtils.isEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("토큰이 존재하지 않습니다.");
        }

        String token = jwtAuthProvider.extractToken(authorizationHeader);
        if (!jwtAuthProvider.validateToken(token)){
            throw new UnauthorizedException("토큰의 값이 유효하지 않습니다.");
        }

        return true;
    }
}