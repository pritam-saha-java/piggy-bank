package com.piggybank.piggybank.exception;

public class TransactionLimitException extends RuntimeException{

    public TransactionLimitException(String message){
        super(message);
    }
}
