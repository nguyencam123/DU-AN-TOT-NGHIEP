package com.example.demo.repositories;

import com.example.demo.models.Located;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocatedRepository extends JpaRepository<Located, Integer> {
}
