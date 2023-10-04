package com.example.demo.services.impl;

import com.example.demo.entities.ImgHotel;
import com.example.demo.repositories.ImgHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgHotelServiceImpl {
    @Autowired
    private ImgHotelRepository imgHotelRepository;

    public List<ImgHotel> getAll(){
        return imgHotelRepository.findAll();
    }
}
