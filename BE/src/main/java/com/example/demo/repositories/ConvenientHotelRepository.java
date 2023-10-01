package com.example.demo.repositories;

import com.example.demo.models.ConvenientHotel;
import com.example.demo.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenientHotelRepository extends JpaRepository<ConvenientHotel, Hotel> {
}
