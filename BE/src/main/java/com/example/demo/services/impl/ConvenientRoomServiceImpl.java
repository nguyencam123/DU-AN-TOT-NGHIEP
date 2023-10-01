package com.example.demo.services.impl;

import com.example.demo.models.ConvenientRoom;
import com.example.demo.repositories.ConvenientRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenientRoomServiceImpl {

    @Autowired
    private ConvenientRoomRepository convenientRoomRepository;

    public List<ConvenientRoom> getAll(){
        return convenientRoomRepository.findAll();
    }
}
