package com.youapp.YouApp.common.exceptions;

public class UserNotFoundExeption extends RuntimeException {
    public UserNotFoundExeption(String message) {
        super(message);
    }
}
