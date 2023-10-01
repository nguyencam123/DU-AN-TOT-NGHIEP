package com.example.demo.services.impl;

import com.example.demo.models.Country;
import com.example.demo.models.ImgHotel;
import com.example.demo.repositories.CountryRepository;
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
