package com.piggybank.piggybank.service;

import com.piggybank.piggybank.entity.TransferEntity;
import com.piggybank.piggybank.request.CreateTransferRequest;
import com.piggybank.piggybank.response.CreateTransferResponse;

import java.util.List;

public interface TransferService {

    TransferEntity createNewTransfer(TransferEntity transfer);

    List<TransferEntity> findAllTransfersFrom24Hours(Long userId);

    CreateTransferResponse createTransfer(CreateTransferRequest request);
}
