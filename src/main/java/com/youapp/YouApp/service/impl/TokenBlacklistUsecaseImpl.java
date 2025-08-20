package com.youapp.YouApp.service.impl;

import com.youapp.YouApp.infrastructure.repository.RedisTokenRepository;
import com.youapp.YouApp.service.TokenBlacklistUsecase;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenBlacklistUsecaseImpl implements TokenBlacklistUsecase {

    private final String REDIS_BLACKLIST_KEY = "blacklist:";
    private final RedisTokenRepository redisTokenRepository;

    public TokenBlacklistUsecaseImpl(RedisTokenRepository redisTokenRepository) {
        this.redisTokenRepository = redisTokenRepository;
    }

    @Override
    public void blacklistToken(String token, String expiredAt) {
        Duration duration = Duration.between(Instant.now(), Instant.parse(expiredAt));
        redisTokenRepository.saveToken(REDIS_BLACKLIST_KEY + token, duration);
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return redisTokenRepository.isTokenBlacklisted(REDIS_BLACKLIST_KEY + token);
    }
}
