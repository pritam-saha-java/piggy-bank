package com.piggybank.piggybank.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("User wealth not found");
    }

    public UserNotFoundException(String username) {
        super("Could not find user " + username);
    }
}
