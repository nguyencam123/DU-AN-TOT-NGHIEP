package com.example.demo.controllers;

import com.example.demo.entities.DetailRoom;
import com.example.demo.entities.Hotel;
import com.example.demo.services.DetailRoomService;
import com.example.demo.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/room")
public class DetailRoomController {

    @Autowired
    private DetailRoomService detailRoomService;

    @GetMapping("")
    public List<DetailRoom> getAll(){
        return detailRoomService.getAll();
    }
}
