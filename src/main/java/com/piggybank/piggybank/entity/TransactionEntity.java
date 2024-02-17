package com.piggybank.piggybank.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private boolean isBought;

    private String currency;

    private BigDecimal amount;

    private Date transactionTime;

    public TransactionEntity(Long userId,
                             boolean isBuying,
                             String currency, BigDecimal amount) {

    }
}
