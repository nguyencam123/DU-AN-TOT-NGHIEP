package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ServicePackRepository.NAME)
public interface ServicePackRepository extends JpaRepository<ServicePack, String> {
    public static final String NAME = "BaseServicePackRepository";
}
