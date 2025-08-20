package com.youapp.YouApp.service.impl;

import com.youapp.YouApp.common.exceptions.UserNotFoundExeption;
import com.youapp.YouApp.infrastructure.dto.LoginRequestDTO;
import com.youapp.YouApp.infrastructure.dto.LoginResponseDTO;
import com.youapp.YouApp.service.LoginUsecase;
import com.youapp.YouApp.service.TokenGeneratorUsecase;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginUsecaseImpl implements LoginUsecase {

    private final AuthenticationManager authenticationManager;
    private final TokenGeneratorUsecase tokenGeneratorUsecase;

    public LoginUsecaseImpl(AuthenticationManager authenticationManager, TokenGeneratorUsecase tokenGeneratorUsecase) {
        this.authenticationManager = authenticationManager;
        this.tokenGeneratorUsecase = tokenGeneratorUsecase;
    }

    @Override
    public LoginResponseDTO loginUser(LoginRequestDTO requestDTO) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getEmail(), requestDTO.getPassword()));
            String accessToken = tokenGeneratorUsecase.generateAccessToken(authentication);

            return new LoginResponseDTO(accessToken);
        }
        catch (AuthenticationException e){
            throw new UserNotFoundExeption("Wrong credentials");
        }
    }
}
