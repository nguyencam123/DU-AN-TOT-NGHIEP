package com.example.demo.services.impl;

import com.example.demo.entities.Room;
import com.example.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAll(){
        return roomRepository.findAll();
    }
}
