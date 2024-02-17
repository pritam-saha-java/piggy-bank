package com.piggybank.piggybank.service;

import com.piggybank.piggybank.entity.WealthEntity;

import java.math.BigDecimal;
import java.util.Map;

public interface WealthService {

    void newWealthRecord(Long userId);

    WealthEntity findWealth(Long userId);

    Map<String, Double> getCurrencyRates();

    void makeWealthExchange(Long userId,
                            String currency,
                            BigDecimal amount,
                            boolean isBuying);

    void makeWealthTransaction(Long userId,
                               String currency,
                               BigDecimal amount,
                               boolean isIncrementing);
}
