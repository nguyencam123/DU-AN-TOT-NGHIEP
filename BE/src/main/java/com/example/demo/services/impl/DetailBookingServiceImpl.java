package com.example.demo.services.impl;

import com.example.demo.models.Country;
import com.example.demo.models.DetailBooking;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.DetailBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailBookingServiceImpl {

    @Autowired
    private DetailBookingRepository detailBookingRepository;

    public List<DetailBooking> getAll(){
        return detailBookingRepository.findAll();
    }
}
