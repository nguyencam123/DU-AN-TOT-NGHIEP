package com.example.demo.repositories;

import com.example.demo.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(SaleRepository.NAME)
public interface SaleRepository extends JpaRepository<Sale, String> {
    public static final String NAME = "BaseSaleRepository";

}
