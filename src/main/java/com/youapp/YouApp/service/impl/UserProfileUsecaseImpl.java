package com.youapp.YouApp.service.impl;

import com.youapp.YouApp.common.exceptions.UserNotFoundExeption;
import com.youapp.YouApp.entity.User;
import com.youapp.YouApp.infrastructure.dto.UserRequestDTO;
import com.youapp.YouApp.infrastructure.dto.UserResponseDTO;
import com.youapp.YouApp.infrastructure.repository.UserRepository;
import com.youapp.YouApp.infrastructure.security.Claims;
import com.youapp.YouApp.service.UserProfileUsecase;
import org.springframework.stereotype.Service;

@Service
public class UserProfileUsecaseImpl implements UserProfileUsecase {

    private final UserRepository userRepository;

    public UserProfileUsecaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createProfile(UserRequestDTO req) {
        Long userId = Claims.getUserIdFromJwt();

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundExeption("User not found !"));

        if (req.getName() != null){
            user.setName(req.getName());
        }
        if (req.getBirthday() != null){
            user.setBirthday(req.getBirthday());
        }
        if (req.getHeight() != null){
            user.setHeight(req.getHeight());
        }
        if (req.getWeight() != null){
            user.setWeight(req.getWeight());
        }
        if (req.getHeight() != null){
            user.setHeight(req.getHeight());
        }

        userRepository.save(user);

    }

    @Override
    public void updateProfile(UserResponseDTO req) {

    }

    @Override
    public UserResponseDTO getProfile() {
        return null;
    }
}
