package com.youapp.YouApp.service;

public interface TokenBlacklistUsecase {

    void blacklistToken(String token, String expiredAt);
    boolean isTokenBlacklisted(String token);
}
