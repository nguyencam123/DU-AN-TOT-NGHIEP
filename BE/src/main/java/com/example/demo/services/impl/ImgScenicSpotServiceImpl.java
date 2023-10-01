package com.example.demo.services.impl;

import com.example.demo.models.Country;
import com.example.demo.models.ImgScenicSpot;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.ImgScenicSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgScenicSpotServiceImpl {
    @Autowired
    private ImgScenicSpotRepository imgScenicSpotRepository;

    public List<ImgScenicSpot> getAll(){
        return imgScenicSpotRepository.findAll();
    }
}
