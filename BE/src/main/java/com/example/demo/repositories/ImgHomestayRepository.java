package com.example.demo.repositories;

import com.example.demo.entities.ImgHomestay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ImgHomestayRepository.NAME)
public interface ImgHomestayRepository extends JpaRepository<ImgHomestay, String> {
    public static final String NAME = "BaseImgHomestayRepository";
}
