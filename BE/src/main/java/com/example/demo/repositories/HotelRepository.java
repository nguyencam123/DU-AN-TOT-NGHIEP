package com.example.demo.repositories;

import com.example.demo.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository(HotelRepository.NAME)
public interface HotelRepository extends JpaRepository<Hotel, String> {
    public static final String NAME = "BaseHotelRepository";

}
