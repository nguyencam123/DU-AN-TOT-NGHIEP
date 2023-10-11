package com.example.demo.repositories;

import com.example.demo.entities.ConvenientHotelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ConvenientHotelTypeRepository.NAME)
public interface ConvenientHotelTypeRepository extends JpaRepository<ConvenientHotelType, String> {
    public static final String NAME = "BaseConvenientHotelTypeRepository";
}
