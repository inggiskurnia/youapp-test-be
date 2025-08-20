package com.youapp.YouApp.infrastructure.controller;

import com.youapp.YouApp.common.response.ApiResponse;
import com.youapp.YouApp.infrastructure.dto.LoginRequestDTO;
import com.youapp.YouApp.infrastructure.dto.RegisterRequestDTO;
import com.youapp.YouApp.infrastructure.dto.UserRequestDTO;
import com.youapp.YouApp.service.LoginUsecase;
import com.youapp.YouApp.service.LogoutUsecase;
import com.youapp.YouApp.service.RegisterUsecase;
import com.youapp.YouApp.service.UserProfileUsecase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final RegisterUsecase registerUsecase;
    private final LoginUsecase loginUsecase;
    private final LogoutUsecase logoutUsecase;
    private final UserProfileUsecase userProfileUsecase;

    public UserController(RegisterUsecase registerUsecase, LoginUsecase loginUsecase, LogoutUsecase logoutUsecase, UserProfileUsecase userProfileUsecase) {
        this.registerUsecase = registerUsecase;
        this.loginUsecase = loginUsecase;
        this.logoutUsecase = logoutUsecase;
        this.userProfileUsecase = userProfileUsecase;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO requestDTO){
        registerUsecase.registerUser(requestDTO);
        return ApiResponse.successfulResponse("Successfully create new user");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO requestDTO){
        return ApiResponse.successfulResponse("Successfully create new user", loginUsecase.loginUser(requestDTO));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(){
        logoutUsecase.logoutUser();
        return ApiResponse.successfulResponse("Logout user success !");
    }

    @PostMapping("/createProfile")
    public ResponseEntity<?> createProfile(@RequestBody UserRequestDTO requestDTO){
        userProfileUsecase.createProfile(requestDTO);
        return ApiResponse.successfulResponse("Successfully create user profile");
    }
}
