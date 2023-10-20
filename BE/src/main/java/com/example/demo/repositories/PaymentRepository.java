package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(PaymentRepository.NAME)
public interface PaymentRepository extends JpaRepository<Payment, String> {
    public static final String NAME = "BasePaymentRepository";

}
