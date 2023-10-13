package com.example.demo.repositories;

import com.example.demo.entities.ConvenientHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ConvenientHotelRepository.NAME)
public interface ConvenientHotelRepository extends JpaRepository<ConvenientHotel, String> {
    public static final String NAME = "BaseConvenientHotelRepository";
}
