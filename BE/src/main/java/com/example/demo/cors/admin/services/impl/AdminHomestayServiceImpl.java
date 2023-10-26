package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminChangeStatusHomestayRequest;
import com.example.demo.cors.admin.model.response.AdminHomestayResponse;
import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.admin.repository.AdminHomestayRepository;
import com.example.demo.cors.admin.services.AdminHomestayService;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.Homestay;
import com.example.demo.infrastructure.contant.Status;
import com.example.demo.repositories.HomestayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminHomestayServiceImpl implements AdminHomestayService {

    @Autowired
    private AdminHomestayRepository adminHotelRepository;

    @Autowired
    private HomestayRepository homestayRepository;

    @Override
    public PageableObject<AdminHomestayResponse> getAll(AdminHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<AdminHomestayResponse> adminHomestayReponsis= adminHotelRepository.getAll(pageable,request);
        return new PageableObject<>(adminHomestayReponsis);
    }

    @Override
    public Homestay changeStatus(AdminChangeStatusHomestayRequest adminChangeStatusHomestayRequest) {
        Optional<Homestay> homestay = homestayRepository.findById(adminChangeStatusHomestayRequest.getHomestayId());
        homestay.get().setStatus(Status.valueOf(String.valueOf(adminChangeStatusHomestayRequest.getStatus())));
        homestayRepository.save(homestay.get());
        return homestay.get();
    }


}
