package com.example.demo.repositories;

import com.example.demo.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(TransactionRepository.NAME)
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    public static final String NAME = "BaseTransactionRepository";
}
