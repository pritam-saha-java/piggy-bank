package com.piggybank.piggybank.request;

import lombok.Data;

@Data
public class FindAllTransactionsByUserRequest {

    private String username;
}
