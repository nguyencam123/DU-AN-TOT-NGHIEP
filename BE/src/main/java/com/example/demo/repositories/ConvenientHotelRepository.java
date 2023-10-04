package com.example.demo.repositories;

import com.example.demo.entities.ConvenientHotel;
import com.example.demo.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenientHotelRepository extends JpaRepository<ConvenientHotel, Hotel> {
}
