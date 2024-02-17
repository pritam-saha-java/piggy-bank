package com.piggybank.piggybank.response;

import com.piggybank.piggybank.entity.TransactionEntity;
import lombok.Data;

import java.util.List;
@Data
public class FindAllTransactionsByUserResponse {

    private List<TransactionEntity> transactionList;
}
