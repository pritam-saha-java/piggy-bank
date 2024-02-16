package com.piggybank.piggybank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WealthRepository extends JpaRepository<WealthRepository, Long> {

}
