package com.youapp.YouApp.service;

import org.springframework.security.core.Authentication;

public interface TokenGeneratorUsecase {
    String generateAccessToken(Authentication authentication);
    String generateRefreshToken(Authentication authentication);
}
