package com.piggybank.piggybank.exception;

public class DailyOperationLimitReachedException extends RuntimeException{

    public DailyOperationLimitReachedException(String message){
        super(message);
    }

    public DailyOperationLimitReachedException() {
        super("Daily transaction limit is reached.");
    }
}
