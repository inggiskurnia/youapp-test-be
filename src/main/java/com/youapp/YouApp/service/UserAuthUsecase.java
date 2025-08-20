package com.youapp.YouApp.service;

import com.youapp.YouApp.infrastructure.dto.LoginRequestDTO;
import com.youapp.YouApp.infrastructure.dto.LoginResponseDTO;
import com.youapp.YouApp.infrastructure.dto.RegisterRequestDTO;

public interface UserAuthUsecase {
    LoginResponseDTO loginUser(LoginRequestDTO req);
    void registerUser(RegisterRequestDTO req);
}
