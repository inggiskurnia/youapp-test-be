package com.youapp.YouApp.service.impl;

import com.youapp.YouApp.infrastructure.security.Claims;
import com.youapp.YouApp.service.LogoutUsecase;
import com.youapp.YouApp.service.TokenBlacklistUsecase;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

@Service
public class LogoutUsecaseImpl implements LogoutUsecase {

    private final JwtDecoder jwtDecoder;
    private final TokenBlacklistUsecase tokenBlacklistUsecase;

    public LogoutUsecaseImpl(JwtDecoder jwtDecoder, TokenBlacklistUsecase tokenBlacklistUsecase) {
        this.jwtDecoder = jwtDecoder;
        this.tokenBlacklistUsecase = tokenBlacklistUsecase;
    }

    @Override
    public void logoutUser() {
        String accessToken = Claims.getJwtTokenString();
        String accessTokenExpiryDate = Claims.getExpirationDateFromJwt();

        tokenBlacklistUsecase.blacklistToken(accessToken, accessTokenExpiryDate);
    }
}
