package com.jackdang.common.auth;

import com.jackdang.common.exception.UnauthorizedException;
import com.jackdang.controller.members.dto.AuthDto.AuthLoginRequest;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Slf4j
@Component
public class JwtAuthProvider {

    @Value("${jwt.secretKey}") // (yml에 위치) springframework beans factory의 annotation을 사용해야 함
    private String secretKey;

    /**
     * JWT 토큰 생성
     */
    public String createToken(AuthLoginRequest authLoginRequest, LocalDateTime now, LocalDateTime exp){
        Date nowDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Date expDate = Date.from(exp.atZone(ZoneId.systemDefault()).toInstant());

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT"); //.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        // headers.put("alg, HS256"); // 적용시, BASE64처럼 임의로 디코딩 불가

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("phone", authLoginRequest.getPhone());
        payloads.put("password", authLoginRequest.getPassword());

        System.out.println("[TEST]" + authLoginRequest.getPhone() + authLoginRequest.getPassword());

        return Jwts.builder()
                .setHeader(headers) // Header 설정
                .setSubject("userToken") // 토큰 제목
                .setClaims(payloads) // Payload 설정 (클레임은 토큰에서 사용할 정보의 조각들로, 페이로드에 저장됨)
                .setIssuer("JACKDANG")   // 토큰 발급자
                .setIssuedAt(nowDate)    // 발급시간
                .setExpiration(expDate)  // 만료시간
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes())) // 알고리즘, 키로 sign
                .compact(); // 토큰 생성
    }

    /**
     * 전체 토큰 유효성 검사
     */
    public boolean validateToken(String token){
        AuthLoginRequest authLoginRequest = getAuthToken(token);
        return Objects.nonNull(authLoginRequest);
    }

    public AuthLoginRequest getAuthToken(String token){
        Claims claims = parseJwtToken(token);
        String phone = claims.get("phone", String.class);
        String password = claims.get("password", String.class);

        return new AuthLoginRequest(phone, password);
    }

    /**
     * 유효한 토큰인지 검증 후, 토큰에 담긴 payload 값 가져옴
     */
    private Claims parseJwtToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                    .parseClaimsJws(token) // 파싱 및 검증 실패시 에러
                    .getBody();
        } catch (ExpiredJwtException e){ // 토큰 만료
            log.info(e.getMessage());
            throw new UnauthorizedException("토큰의 유효기간이 만료되었습니다.");
        } catch (Exception e){ // 그외 에러
            log.error(e.getMessage());
            throw new UnauthorizedException();
        }
    }

    /**
     * 인증 헤더 문자열에서 토큰값 추출하기
     */
    public String extractToken(String authrizationHeader){
        return authrizationHeader.substring("Bearer ".length());
    }
}