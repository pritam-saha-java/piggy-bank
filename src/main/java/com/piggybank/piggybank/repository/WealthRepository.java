package com.piggybank.piggybank.repository;

import com.piggybank.piggybank.entity.WealthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WealthRepository extends JpaRepository<WealthEntity, Long> {

}
