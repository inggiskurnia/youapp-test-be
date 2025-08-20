package com.youapp.YouApp.service;

import com.youapp.YouApp.infrastructure.dto.UserRequestDTO;
import com.youapp.YouApp.infrastructure.dto.UserResponseDTO;

public interface UserProfileUsecase {

    void createProfile(UserRequestDTO req);
    void updateProfile(UserResponseDTO req);
    UserResponseDTO getProfile();

}
