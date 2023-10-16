package com.example.demo.repositories;

import com.example.demo.entities.ServicePack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ServicePackRepository.NAME)
public interface ServicePackRepository extends JpaRepository<ServicePack, String> {
    public static final String NAME = "BaseServicePackRepository";
}
