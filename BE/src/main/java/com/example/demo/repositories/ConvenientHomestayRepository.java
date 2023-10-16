package com.example.demo.repositories;

import com.example.demo.entities.ConvenientHomestay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ConvenientHomestayRepository.NAME)
public interface ConvenientHomestayRepository extends JpaRepository<ConvenientHomestay, String> {
    public static final String NAME = "BaseConvenientHomestayRepository";
}
