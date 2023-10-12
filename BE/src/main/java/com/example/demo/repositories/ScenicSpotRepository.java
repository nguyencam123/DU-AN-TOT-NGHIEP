package com.example.demo.repositories;

import com.example.demo.entities.ScenicSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ScenicSpotRepository.NAME)
public interface ScenicSpotRepository extends JpaRepository<ScenicSpot , String> {
    public static final String NAME = "BaseScenicSpotRepository";
}
