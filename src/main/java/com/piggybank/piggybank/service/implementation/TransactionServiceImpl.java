package com.piggybank.piggybank.service.implementation;

import com.piggybank.piggybank.entity.TransactionEntity;
import com.piggybank.piggybank.repository.TransactionRepository;
import com.piggybank.piggybank.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }


    @Override
    public TransactionEntity createNewTransaction(Long userId, boolean isBuying, String currency, BigDecimal amount) {
        TransactionEntity transaction = new TransactionEntity(userId, isBuying, currency, amount);
        return repository.save(transaction);
    }


    @Override
    public int getOperationCountFromLast24Hours(Long userId) {
        return repository.getOperationCountFromLast24Hours(userId);
    }

    @Override
    public List<TransactionEntity> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }
}
