package com.youapp.YouApp.service;

import com.youapp.YouApp.infrastructure.dto.RegisterRequestDTO;

public interface RegisterUsecase {
    void registerUser(RegisterRequestDTO req);
}
