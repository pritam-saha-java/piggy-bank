package com.piggybank.piggybank.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
    private String tcno;
}
