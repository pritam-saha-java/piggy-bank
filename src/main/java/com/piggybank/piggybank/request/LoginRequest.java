package com.piggybank.piggybank.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}
