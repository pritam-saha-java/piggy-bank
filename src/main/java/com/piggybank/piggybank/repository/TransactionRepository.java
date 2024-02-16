package com.piggybank.piggybank.repository;

import com.piggybank.piggybank.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Query(value = "SELECT COUNT(*) FROM TransactionEntity WHERE userId = :userId and transactionTime >= DATEADD(day, -1, GETDATE())")
    int getOperationCountFromLast24Hours(@Param("userId") Long userId);

    List<TransactionEntity> findAllByUserId(Long userId);

}
