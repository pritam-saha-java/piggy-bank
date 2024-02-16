package com.piggybank.piggybank.repository;

import com.piggybank.piggybank.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

    @Query(value = "SELECT t FROM TransferEntity t WHERE t.fromUserId = :userId and t.transferTime >= DATEADD(day, -1, GETDATE())")
    List<TransferEntity> findAllTransfersFrom24Hours(@Param("userId") Long userId);

}
