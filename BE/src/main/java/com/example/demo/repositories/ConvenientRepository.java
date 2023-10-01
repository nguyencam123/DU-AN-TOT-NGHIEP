package com.example.demo.repositories;

import com.example.demo.models.Convenient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenientRepository extends JpaRepository<Convenient, Integer> {
}
