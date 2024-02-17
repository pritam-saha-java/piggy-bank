package com.piggybank.piggybank.controller;

import com.piggybank.piggybank.request.CreateTransferRequest;
import com.piggybank.piggybank.response.CreateTransferResponse;
import com.piggybank.piggybank.service.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transfer")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping(path = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<CreateTransferResponse> createTransfer(@RequestBody CreateTransferRequest request) {
        CreateTransferResponse response = transferService.createTransfer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
