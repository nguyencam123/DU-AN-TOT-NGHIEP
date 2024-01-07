package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminBookingByHomestayRequest;
import com.example.demo.cors.admin.model.request.AdminBookingRequest;
import com.example.demo.cors.admin.model.response.AdminBookingResponse;
import com.example.demo.cors.admin.repository.AdminBookingRepository;
import com.example.demo.cors.admin.services.AdminBookingService;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.Booking;
import com.example.demo.infrastructure.contant.Message;
import com.example.demo.infrastructure.contant.StatusPayOwner;
import com.example.demo.infrastructure.contant.StatusPayUser;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminBookingServiceImpl implements AdminBookingService {

    @Autowired
    private AdminBookingRepository adminBookingRepository;

    @Override
    public PageableObject<Booking> getAllBooking(AdminBookingRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Booking> getAllBooking = adminBookingRepository.getAllBooking(request, pageable);
        return new PageableObject<>(getAllBooking);
    }

    @Override
    public PageableObject<AdminBookingResponse> getAllBookingByHomestay(AdminBookingByHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<AdminBookingResponse> getAllBookingByHomestay = adminBookingRepository.getAllBookingByHomestay(request, pageable);
        return new PageableObject<>(getAllBookingByHomestay);
    }

    @Override
    public Booking updateAdminTranCode(AdminBookingRequest request) {
        Optional<Booking> optional = adminBookingRepository.findById(request.getId());
        if (!optional.isPresent()) {
            throw new RestApiException(Message.NOT_EXISTS);
        }
        Booking booking = optional.get();
        booking.setAdminTransactionCode(request.getAdminTransactionCode());
        booking.setStatusPayOwner(StatusPayOwner.DA_TT_CHO_OWNER);
        adminBookingRepository.save(booking);
        return booking;
    }


    @Override
    public Booking updateCancellTranCode(AdminBookingRequest request) {
        Optional<Booking> optional = adminBookingRepository.findById(request.getId());
        if (!optional.isPresent()) {
            throw new RestApiException(Message.NOT_EXISTS);
        }
        Booking booking = optional.get();
        booking.setCancellTransactionCode(request.getCancellTransactionCode());
        booking.setStatusPayUser(StatusPayUser.DA_TT_CHO_USER);
        adminBookingRepository.save(booking);
        return booking;
    }
}
