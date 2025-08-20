package com.youapp.YouApp.service.impl;

import com.youapp.YouApp.common.exceptions.UserNotFoundExeption;
import com.youapp.YouApp.entity.User;
import com.youapp.YouApp.infrastructure.dto.UserAuth;
import com.youapp.YouApp.infrastructure.repository.UserRepository;
import com.youapp.YouApp.service.GetUserAuthDetailsUsecase;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetUserAuthDetailsUsecaseImpl implements GetUserAuthDetailsUsecase {

    private final UserRepository userRepository;

    public GetUserAuthDetailsUsecaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User existingUser = userRepository.findByEmailIgnoreCase(username)
                .orElseThrow(()-> new UserNotFoundExeption("Username with email "+ username + " not found !"));

        UserAuth userAuth = new UserAuth();
        userAuth.setUser(existingUser);
        return userAuth;
    }
}
