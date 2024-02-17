package com.piggybank.piggybank.service;

import com.piggybank.piggybank.entity.TransferEntity;

import java.util.List;

public interface TransferService {

    TransferEntity createNewTransfer(TransferEntity transfer);

    List<TransferEntity> findAllTransfersFrom24Hours(Long userId);
}
