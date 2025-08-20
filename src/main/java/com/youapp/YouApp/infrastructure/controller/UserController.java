package com.youapp.YouApp.infrastructure.controller;

import com.youapp.YouApp.common.response.ApiResponse;
import com.youapp.YouApp.infrastructure.dto.LoginRequestDTO;
import com.youapp.YouApp.infrastructure.dto.RegisterRequestDTO;
import com.youapp.YouApp.service.LoginUsecase;
import com.youapp.YouApp.service.RegisterUsecase;
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

    public UserController(RegisterUsecase registerUsecase, LoginUsecase loginUsecase) {
        this.registerUsecase = registerUsecase;
        this.loginUsecase = loginUsecase;
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
}
