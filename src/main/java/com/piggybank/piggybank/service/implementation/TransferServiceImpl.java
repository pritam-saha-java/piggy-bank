package com.piggybank.piggybank.service.implementation;

import com.piggybank.piggybank.entity.TransferEntity;
import com.piggybank.piggybank.repository.TransferRepository;
import com.piggybank.piggybank.service.TransferService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository repository;

    public TransferServiceImpl(TransferRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransferEntity createNewTransfer(TransferEntity transfer) {
        return repository.save(transfer);
    }

    @Override
    public List<TransferEntity> findAllTransfersFrom24Hours(Long userId) {
        return repository.findAllTransfersFrom24Hours(userId);
    }
}
