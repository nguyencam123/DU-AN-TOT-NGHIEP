package com.example.demo.services.impl;

import com.example.demo.entities.DetailRoom;
import com.example.demo.repositories.DetailRoomRepository;
import com.example.demo.services.DetailRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailRoomServiceImpl implements DetailRoomService {

    @Autowired
    private DetailRoomRepository detailRoomRepository;

    @Override
    public List<DetailRoom> getAll() {
        return detailRoomRepository.findAll();
    }
}
