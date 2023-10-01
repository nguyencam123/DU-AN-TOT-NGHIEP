package com.example.demo.repositories;

import com.example.demo.models.CustomerRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRankRepository extends JpaRepository<CustomerRank, Integer> {
}
