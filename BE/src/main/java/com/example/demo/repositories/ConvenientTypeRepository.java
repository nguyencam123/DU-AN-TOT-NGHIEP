package com.example.demo.repositories;

import com.example.demo.models.ConvenientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenientTypeRepository extends JpaRepository<ConvenientType, Integer> {
}
