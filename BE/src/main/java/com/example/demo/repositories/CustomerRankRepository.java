package com.example.demo.repositories;

import com.example.demo.entities.CustomerRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(CustomerRankRepository.NAME)
public interface CustomerRankRepository extends JpaRepository<CustomerRank, String> {
    public static final String NAME = "BaseCustomerRankRepository";
}
