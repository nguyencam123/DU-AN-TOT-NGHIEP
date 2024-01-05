package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerBookingRequest;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerBookingRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerBookingService;
import com.example.demo.entities.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class HomestayOwnerBookingServiceImpl implements HomestayOwnerBookingService {

    @Autowired
    private HomestayOwnerBookingRepository homestayOwnerBookingRepository;

    @Override
    public PageableObject<Booking> getBookingByHomestay(String id, HomestayOwnerBookingRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Booking> res = homestayOwnerBookingRepository.getBookingByOwnerHomestay(id, pageable);
        return new PageableObject<>(res);
    }

    @Override
    public PageableObject<Booking> getAllBooking(HomestayOwnerBookingRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Booking> getAllBooking = homestayOwnerBookingRepository.getAllBooking(request, pageable);
        return new PageableObject<>(getAllBooking);
    }

    @Override
    public PageableObject<Booking> getBookingByYearAndMonth(HomestayOwnerBookingRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Booking> getAllBooking = homestayOwnerBookingRepository.getBookingByYearAndMonth(request, pageable);
        return new PageableObject<>(getAllBooking);
    }

}
