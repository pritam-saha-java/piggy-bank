package com.piggybank.piggybank.service.implementation;

import com.mysql.cj.util.StringUtils;
import com.piggybank.piggybank.configuration.Constants;
import com.piggybank.piggybank.entity.TransactionEntity;
import com.piggybank.piggybank.entity.UserEntity;
import com.piggybank.piggybank.exception.BadRequestException;
import com.piggybank.piggybank.exception.DailyOperationLimitReachedException;
import com.piggybank.piggybank.repository.TransactionRepository;
import com.piggybank.piggybank.request.CreateTransactionRequest;
import com.piggybank.piggybank.request.FindAllTransactionsByUserRequest;
import com.piggybank.piggybank.response.CreateTransactionResponse;
import com.piggybank.piggybank.response.FindAllTransactionsByUserResponse;
import com.piggybank.piggybank.service.TransactionService;
import com.piggybank.piggybank.service.UserService;
import com.piggybank.piggybank.service.WealthService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final UserService userService;
    private final TransactionService transactionService;
    private final WealthService wealthService;

    public TransactionServiceImpl(TransactionRepository repository,
                                  UserService userService,
                                  TransactionService transactionService,
                                  WealthService wealthService) {
        this.repository = repository;
        this.userService = userService;
        this.transactionService = transactionService;
        this.wealthService = wealthService;
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

    @Override
    public CreateTransactionResponse createTransaction(CreateTransactionRequest request){

        if (StringUtils.isNullOrEmpty(request.getUsername())) {
            throw new BadRequestException(Constants.MESSAGE_INVALIDUSERNAME);

        } else if (StringUtils.isNullOrEmpty(request.getCurrency())) {
            throw new BadRequestException(Constants.MESSAGE_INVALIDCURRENCY);

        } else if (Objects.nonNull(request.getAmount())) {
            throw new BadRequestException(Constants.MESSAGE_INVALIDAMOUNT);

        }

        //get user details
        UserEntity user = userService.findByUserName(request.getUsername());

        //find transactions in last 24 hours
        int last24HoursOperationCount = transactionService.getOperationCountFromLast24Hours(user.getId());
        if (last24HoursOperationCount >= 10) {
            throw new DailyOperationLimitReachedException();
        }

        wealthService.makeWealthExchange(user.getId(), request.getCurrency(), request.getAmount(), request.isBuying());

        TransactionEntity transaction = transactionService.createNewTransaction(user.getId(), request.isBuying(),
                request.getCurrency(), request.getAmount());

        CreateTransactionResponse response = new CreateTransactionResponse();
        response.setTransaction(transaction);
        return response;
    }

    @Override
    public FindAllTransactionsByUserResponse findAll(FindAllTransactionsByUserRequest request){

        if (StringUtils.isNullOrEmpty(request.getUsername())) {
            throw new BadRequestException(Constants.MESSAGE_INVALIDUSERNAME);
        }

        UserEntity user = userService.findByUserName(request.getUsername());
        List<TransactionEntity> transactionList = transactionService.findAllByUserId(user.getId());

        FindAllTransactionsByUserResponse response = new FindAllTransactionsByUserResponse();
        response.setTransactionList(transactionList);
        return response;
    }
}
