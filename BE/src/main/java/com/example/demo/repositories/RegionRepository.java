package com.example.demo.repositories;

import com.example.demo.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(RegionRepository.NAME)
public interface RegionRepository extends JpaRepository<Region, String> {
    public static final String NAME = "BaseRegionRepository";
}