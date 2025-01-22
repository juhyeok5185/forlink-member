package com.danny.forlinkbackendspringboot.common.config;

import com.danny.forlinkbackendspringboot.common.util.AES256Utils;
import com.danny.forlinkbackendspringboot.member.Member;
import com.danny.forlinkbackendspringboot.member.MemberReader;
import com.danny.forlinkbackendspringboot.member.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String SECRET_KEY = "iJ6tP8bJKd8fDmtBt79xs0A5ZRqnNEmFsgMTN5WXY9g=";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace("Bearer", "").trim();

        try {
            Key signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)); // Base64 키 디코딩
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();


            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                            Collections.singletonList(new SimpleGrantedAuthority(claims.get("role", String.class))));

            System.out.println("JWT Role Claim: " + claims.get("role", String.class));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Granted Authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());


        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid JWT token");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
