package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.response.AdminHomestayResponse;
import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.admin.repository.AdminHomestayRepository;
import com.example.demo.cors.admin.services.AdminHomestayService;
import com.example.demo.cors.common.base.PageableObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminHomestayServiceImpl implements AdminHomestayService {

    @Autowired
    private AdminHomestayRepository adminHotelRepository;

    @Override
    public PageableObject<AdminHomestayResponse> getAll(AdminHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<AdminHomestayResponse> adminHomestayReponsis= adminHotelRepository.getAll(pageable,request);
        return new PageableObject<>(adminHomestayReponsis);
    }


}
