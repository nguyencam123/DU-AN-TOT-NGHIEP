package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminApprovalRequest;
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
    private AdminHomestayRepository adminHomestayRepository;

    @Override
    public PageableObject<Homestay> getAllChoDuyet(AdminHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<Homestay> adminHomestayReponsis= adminHomestayRepository.getAllChoDuyet(pageable,request);
        return new PageableObject<>(adminHomestayReponsis);
    }

    @Override
    public PageableObject<Homestay> getAllDaDuyet(AdminHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<Homestay> adminHomestayReponsis= adminHomestayRepository.getAllDaDuyet(pageable,request);
        return new PageableObject<>(adminHomestayReponsis);
    }

    @Override
    public PageableObject<Homestay> getAllDaXoa(AdminHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<Homestay> adminHomestayReponsis= adminHomestayRepository.getAllDaXoa(pageable,request);
        return new PageableObject<>(adminHomestayReponsis);
    }

    @Override
    public PageableObject<Homestay> getAllKhongDuyet(AdminHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<Homestay> adminHomestayReponsis= adminHomestayRepository.getAllKhongDuyet(pageable,request);
        return new PageableObject<>(adminHomestayReponsis);
    }

    @Override
    public PageableObject<Homestay> getAllHomestay(AdminHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<Homestay> adminHomestayReponsis= adminHomestayRepository.findAll(pageable);
        return new PageableObject<>(adminHomestayReponsis);
    }

    @Override
    public PageableObject<Homestay> getAllById(AdminHomestayRequest adminHomestayRequest) {
        Pageable pageable = PageRequest.of(adminHomestayRequest.getPage(),adminHomestayRequest.getSize());
        Page<Homestay> getAllbyid= adminHomestayRepository.getAllByID(pageable,adminHomestayRequest);
        return new PageableObject<>(getAllbyid);
    }

    @Override
    public PageableObject<Homestay> findByNameChoDuyet(AdminHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<Homestay> findByNameChoDuyet= adminHomestayRepository.findByNameChoDuyet(pageable,request);
        return new PageableObject<>(findByNameChoDuyet);
    }

    @Override
    public PageableObject<Homestay> findByNameDaDuyet(AdminHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
        Page<Homestay> findByNameDaDuyet= adminHomestayRepository.findByNameDaDuyet(pageable,request);
        return new PageableObject<>(findByNameDaDuyet);
    }


}
