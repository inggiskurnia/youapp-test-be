package com.youapp.YouApp.service;

import com.youapp.YouApp.infrastructure.dto.LoginRequestDTO;
import com.youapp.YouApp.infrastructure.dto.LoginResponseDTO;

public interface LoginUsecase {
    LoginResponseDTO loginUser(LoginRequestDTO requestDTO);
}
