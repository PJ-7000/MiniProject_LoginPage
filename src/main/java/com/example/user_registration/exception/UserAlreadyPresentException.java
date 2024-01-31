package com.example.user_registration.exception;

public class UserAlreadyPresentException extends RuntimeException {

    public UserAlreadyPresentException(String message) {
        super(message);
    }
}