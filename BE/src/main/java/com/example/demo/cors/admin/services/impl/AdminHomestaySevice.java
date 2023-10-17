package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.reponse.AdminHomestayReponsi;
import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.admin.repository.AdminHomestayRepository;
import com.example.demo.cors.admin.services.IAdminHomestaySevice;
import com.example.demo.cors.common.base.PageableObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminHomestaySevice implements IAdminHomestaySevice {

    @Autowired
    private AdminHomestayRepository adminHotelRepository;

    @Override
    public PageableObject<AdminHomestayReponsi> getAll(AdminHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<AdminHomestayReponsi> adminHomestayReponsis= adminHotelRepository.getAll(pageable,request);
        return new PageableObject<>(adminHomestayReponsis);
    }


}
