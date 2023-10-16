package com.example.demo.repositories;

import com.example.demo.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ProvinceRepository.NAME)
public interface ProvinceRepository extends JpaRepository<Province, String> {
    public static final String NAME = "BaseProvinceRepository";
}