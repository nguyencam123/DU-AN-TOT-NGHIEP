package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.admin.repository.AdminHomestayRepository;
import com.example.demo.cors.admin.repository.AdminLoginRepository;
import com.example.demo.cors.admin.services.AdminHomestayService;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.Admin;
import com.example.demo.entities.Homestay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminHomestayServiceImpl implements AdminHomestayService {

    @Autowired
    private AdminHomestayRepository adminHomestayRepository;

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    @Override
    public PageableObject<Homestay> getAllHomestay(AdminHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Homestay> adminHomestayResponse = adminHomestayRepository.getAllHomestay(pageable, request);
        return new PageableObject<>(adminHomestayResponse);
    }

    @Override
    public Admin getAdminByToken(String token) {
        return adminLoginRepository.findAdminByToken(token);
    }

}
