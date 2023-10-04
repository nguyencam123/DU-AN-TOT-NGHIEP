package com.example.demo.services.impl;

import com.example.demo.entities.CancellationPolicyRoom;
import com.example.demo.repositories.CancellationPolicyRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancellationPolicyServiceImpl {

    @Autowired
    private CancellationPolicyRoomRepository cancellationPolicyRoomRepository;

    public List<CancellationPolicyRoom> getAll(){
        return  cancellationPolicyRoomRepository.findAll();
    }
}
