package com.example.demo.services.impl;

import com.example.demo.entities.ConvenientHotel;
import com.example.demo.repositories.ConvenientHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenientHotelServiceImpl {

    @Autowired
    private ConvenientHotelRepository convenientHotelRepository;

    public List<ConvenientHotel> getAll(){
        return  convenientHotelRepository.findAll();
    }
}
