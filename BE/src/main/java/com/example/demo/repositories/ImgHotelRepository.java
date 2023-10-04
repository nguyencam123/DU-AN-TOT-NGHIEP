package com.example.demo.repositories;

import com.example.demo.entities.ImgHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgHotelRepository extends JpaRepository<ImgHotel, Integer> {
}
