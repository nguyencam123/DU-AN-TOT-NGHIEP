package com.example.demo.cors.homestayowner.service.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerBookingReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerBookingRequest;
import com.example.demo.cors.homestayowner.repository.HomestayOwnerBookingRepository;
import com.example.demo.cors.homestayowner.service.HomestayOwnerBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HomestayOwnerBookingServiceImpl implements HomestayOwnerBookingService {

    @Autowired
    private HomestayOwnerBookingRepository homestayOwnerBookingRepository;

    @Override
    public PageableObject<HomestayOwnerBookingReponse> getBookingByHomestay(String id,HomestayOwnerBookingRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<HomestayOwnerBookingReponse> res=homestayOwnerBookingRepository.getBookingByHomestay(id,pageable);
        return new PageableObject<>(res);
    }
}
