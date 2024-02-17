package com.piggybank.piggybank.controller;

import com.piggybank.piggybank.request.CreateTransactionRequest;
import com.piggybank.piggybank.request.FindAllTransactionsByUserRequest;
import com.piggybank.piggybank.response.CreateTransactionResponse;
import com.piggybank.piggybank.response.FindAllTransactionsByUserResponse;
import com.piggybank.piggybank.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(path = "/create" , produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CreateTransactionResponse> createTransaction(@RequestBody CreateTransactionRequest request) {
        CreateTransactionResponse response = transactionService.createTransaction(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(path = "/find/all", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<FindAllTransactionsByUserResponse> findAll(@RequestBody FindAllTransactionsByUserRequest request) {
        FindAllTransactionsByUserResponse response = transactionService.findAll(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
