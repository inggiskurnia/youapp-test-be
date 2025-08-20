package com.youapp.YouApp.service.impl;

import com.youapp.YouApp.common.exceptions.DuplicateUserException;
import com.youapp.YouApp.entity.User;
import com.youapp.YouApp.infrastructure.dto.RegisterRequestDTO;
import com.youapp.YouApp.infrastructure.repository.UserRepository;
import com.youapp.YouApp.service.RegisterUsecase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUsecaseImpl implements RegisterUsecase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUsecaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(RegisterRequestDTO req) {
        Optional<User> userOptional = userRepository.findByEmailIgnoreCase(req.getEmail());

        if (userOptional.isPresent()){
            throw new DuplicateUserException("User with email " + req.getEmail() + " already exist !");
        }

        User newUser = req.toEntity();
        newUser.setPassword(passwordEncoder.encode(req.getPassword()));

        userRepository.save(newUser);

    }
}
