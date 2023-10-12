package com.example.demo.repositories;

import com.example.demo.entities.ImgHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ImgHotelRepository.NAME)
public interface ImgHotelRepository extends JpaRepository<ImgHotel, String> {
    public static final String NAME = "BaseImgHotelRepository";
}
