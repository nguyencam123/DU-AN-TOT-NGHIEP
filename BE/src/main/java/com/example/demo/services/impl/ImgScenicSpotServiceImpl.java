package com.example.demo.services.impl;

import com.example.demo.entities.ImgScenicSpot;
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
