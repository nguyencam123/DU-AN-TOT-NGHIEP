package com.example.demo.services.impl;

import com.example.demo.models.Booking;
import com.example.demo.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAll(){
        return bookingRepository.findAll();
    }
}
