package com.example.demo.services.impl;

import com.example.demo.entities.ImgRoom;
import com.example.demo.repositories.ImgRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgRoomServiceImpl {

    @Autowired
    private ImgRoomRepository imgRoomRepository;

    public List<ImgRoom> getAll(){
        return imgRoomRepository.findAll();
    }
}
