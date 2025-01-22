package com.danny.forlinkbackendspringboot.common.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Date;

public class JWTUtils {

    private static final String SECRET_KEY = "iJ6tP8bJKd8fDmtBt79xs0A5ZRqnNEmFsgMTN5WXY9g="; // 안전한 키 생성
    private static final long EXPIRATION_TIME = 86400000; // 1일 (밀리초 단위)

    public static String generateToken(String loginId, String role) {
        return Jwts.builder()
                .setSubject(loginId)
                .claim("role", "ROLE_" + role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String getLoginId(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            return null;
        }
        return authentication.getPrincipal().toString();
    }

    public static String getRole(Authentication authentication) {
        if (authentication == null || authentication.getAuthorities() == null || authentication.getAuthorities().isEmpty()) {
            return null;
        }
        return authentication.getAuthorities().iterator().next().getAuthority();
    }

}
