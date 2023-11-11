package com.example.demo.cors.admin.repository;

import com.example.demo.repositories.ConvenientHomestayTypeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminConvenientHomestayTypeRespository extends ConvenientHomestayTypeRepository {
    boolean existsByName(String nameType);
}
