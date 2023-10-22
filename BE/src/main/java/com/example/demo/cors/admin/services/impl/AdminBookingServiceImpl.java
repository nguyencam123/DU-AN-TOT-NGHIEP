package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminBookingRequest;
import com.example.demo.cors.admin.model.response.AdminBookingResponse;
import com.example.demo.cors.admin.repository.AdminBookingRepository;
import com.example.demo.cors.admin.services.AdminBookingService;
import com.example.demo.cors.common.base.PageableObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminBookingServiceImpl implements AdminBookingService {

    @Autowired
    private AdminBookingRepository adminBookingRepository;

    @Override
    public PageableObject<AdminBookingResponse> getAllBooking(AdminBookingRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<AdminBookingResponse> getAllBooking = adminBookingRepository.getAllBooking(request, pageable);
        return new PageableObject<>(getAllBooking);
    }

}
