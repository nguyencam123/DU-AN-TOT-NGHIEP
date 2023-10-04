package com.example.demo.repositories;

import com.example.demo.entities.ImgScenicSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgScenicSpotRepository extends JpaRepository<ImgScenicSpot , Integer> {
}
