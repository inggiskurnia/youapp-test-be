package com.youapp.YouApp.service.impl;

import com.youapp.YouApp.common.exceptions.UserNotFoundExeption;
import com.youapp.YouApp.entity.User;
import com.youapp.YouApp.infrastructure.repository.UserRepository;
import com.youapp.YouApp.service.TokenGeneratorUsecase;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenGeneratorUsecaseImpl implements TokenGeneratorUsecase {

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;

    public TokenGeneratorUsecaseImpl(JwtEncoder jwtEncoder, UserRepository userRepository) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public String generateAccessToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 600L;

        String email = authentication.getName();
        User user = userRepository.findByEmailIgnoreCase(email).orElseThrow(()-> new UserNotFoundExeption("User with email " + email + " not found !"));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(email)
                .claim("userId", user.getUserId())
                .claim("tokenType", "access")
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
