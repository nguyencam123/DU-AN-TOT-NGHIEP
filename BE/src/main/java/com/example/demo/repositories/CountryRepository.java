package com.example.demo.repositories;

import com.example.demo.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(CountryRepository.NAME)
public interface CountryRepository extends JpaRepository<Country, String> {
    public static final String NAME = "BaseCountryRepository";
}
