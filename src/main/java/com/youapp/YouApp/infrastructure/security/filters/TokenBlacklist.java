package com.youapp.YouApp.infrastructure.security.filters;

import com.youapp.YouApp.service.TokenBlacklistUsecase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenBlacklist extends OncePerRequestFilter {

    private final TokenBlacklistUsecase tokenBlacklistUsecase;

    public TokenBlacklist(TokenBlacklistUsecase tokenBlacklistUsecase) {
        this.tokenBlacklistUsecase = tokenBlacklistUsecase;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractTokenFromRequest(request);
        if (token != null && tokenBlacklistUsecase.isTokenBlacklisted(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private static String extractTokenFromRequest(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("SID")){
                    return cookie.getValue();
                }
            }
        }

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")){
            return header.substring(7);
        }

        return null;
    }
}
