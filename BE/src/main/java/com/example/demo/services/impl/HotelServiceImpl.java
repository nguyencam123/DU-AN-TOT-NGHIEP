package com.example.demo.services.impl;

import com.example.demo.models.Country;
import com.example.demo.models.Hotel;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAll(){
        return hotelRepository.findAll();
    }
}
