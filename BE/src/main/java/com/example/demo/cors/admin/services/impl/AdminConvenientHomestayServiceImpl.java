package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminConvenientHomestayRequest;
import com.example.demo.cors.admin.model.request.AdminConvenientHomestayTypeRequest;
import com.example.demo.cors.admin.model.response.AdminConvenientHomestayResponse;
import com.example.demo.cors.admin.repository.AdminConvenientHomestayRepository;
import com.example.demo.cors.admin.repository.AdminConvenientHomestayTypeRespository;
import com.example.demo.cors.admin.services.AdminConvenientHomestayService;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.ConvenientHomestay;
import com.example.demo.entities.ConvenientHomestayType;
import com.example.demo.infrastructure.contant.Message;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class AdminConvenientHomestayServiceImpl implements AdminConvenientHomestayService {

    @Autowired
    private AdminConvenientHomestayRepository adminConvenientHomestayRepository;

    @Autowired
    private AdminConvenientHomestayTypeRespository adminConvenientHomestayTypeRespository;


    @Override
    public PageableObject<ConvenientHomestay> getAllConvenient(AdminConvenientHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<ConvenientHomestay> res = adminConvenientHomestayRepository.findAll(pageable);
        return new PageableObject<>(res);
    }

    @Override
    public PageableObject<ConvenientHomestayType> getAllConvenientType(AdminConvenientHomestayTypeRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<ConvenientHomestayType> res = adminConvenientHomestayTypeRespository.findAll(pageable);
        return new PageableObject<>(res);
    }

    @Override
    public ConvenientHomestay addConvenientHomestay( AdminConvenientHomestayRequest adminConvenientHomestayRequest){
        if (isNullOrEmpty(adminConvenientHomestayRequest.getName())) {
            throw new RestApiException("Name cannot be empty");
        }
        if (adminConvenientHomestayRepository.existsByName(adminConvenientHomestayRequest.getName())) {
            throw new RestApiException("Convenient already exist");
        }
        ConvenientHomestay convenientHomestay = new ConvenientHomestay();
        ConvenientHomestayType convenientHomestayType = adminConvenientHomestayTypeRespository.findById(adminConvenientHomestayRequest.getIdType()).orElse(null);
        convenientHomestay.setName(adminConvenientHomestayRequest.getName());
        convenientHomestay.setDesc(adminConvenientHomestayRequest.getDesc());
        convenientHomestay.setConvenientHomestayType(convenientHomestayType);
        ConvenientHomestay convenientHomestay1 = adminConvenientHomestayRepository.save(convenientHomestay);
        return convenientHomestay1;
    }

    @Override
    public ConvenientHomestayType addConvenientHomestayType(AdminConvenientHomestayTypeRequest adminConvenientHomestayTypeRequest) {
        if (isNullOrEmpty(adminConvenientHomestayTypeRequest.getNameType())) {
            throw new RestApiException("Name cannot be empty");
        }
        if (adminConvenientHomestayTypeRespository.existsByName(adminConvenientHomestayTypeRequest.getNameType())) {
            throw new RestApiException("ConvenientType already exist");
        }
        ConvenientHomestayType convenientHomestayType = new ConvenientHomestayType();
        convenientHomestayType.setName(adminConvenientHomestayTypeRequest.getNameType());
        convenientHomestayType.setDesc(adminConvenientHomestayTypeRequest.getDescType());
        ConvenientHomestayType convenientHomestayType1 = adminConvenientHomestayTypeRespository.save(convenientHomestayType);
        return convenientHomestayType1;
    }

    @Override
    public ConvenientHomestay updateConvenientHomestay(AdminConvenientHomestayRequest adminConvenientHomestayRequest) {
        ConvenientHomestay convenientHomestay = adminConvenientHomestayRepository.findById(adminConvenientHomestayRequest.getId()).orElse(null);
        if (isNullOrEmpty(adminConvenientHomestayRequest.getName())) {
            throw new RestApiException("Name cannot be empty");
        }
        if (!adminConvenientHomestayRequest.getName().equals(convenientHomestay.getName()) &&
                adminConvenientHomestayRepository.existsByName(adminConvenientHomestayRequest.getName())) {
            throw new RestApiException("Name Convenient already exists");
        }
        ConvenientHomestayType convenientHomestayType = adminConvenientHomestayTypeRespository.findById(adminConvenientHomestayRequest.getIdType()).orElse(null);
        convenientHomestay.setName(adminConvenientHomestayRequest.getName());
        convenientHomestay.setDesc(adminConvenientHomestayRequest.getDesc());
        convenientHomestay.setConvenientHomestayType(convenientHomestayType);
        ConvenientHomestay convenientHomestay1 = adminConvenientHomestayRepository.save(convenientHomestay);
        return convenientHomestay1;
    }

    @Override
    public ConvenientHomestayType updateConvenientHomestayType(AdminConvenientHomestayTypeRequest adminConvenientHomestayTypeRequest) {
        ConvenientHomestayType convenientHomestayType = adminConvenientHomestayTypeRespository.findById(adminConvenientHomestayTypeRequest.getId()).orElse(null);
        if (isNullOrEmpty(adminConvenientHomestayTypeRequest.getNameType())) {
            throw new RestApiException("Name cannot be empty");
        }
        if (!adminConvenientHomestayTypeRequest.getNameType().equals(convenientHomestayType.getName()) &&
                adminConvenientHomestayTypeRespository.existsByName(adminConvenientHomestayTypeRequest.getNameType())) {
            throw new RestApiException("Name ConvenientType already exists");
        }
        convenientHomestayType.setName(adminConvenientHomestayTypeRequest.getNameType());
        convenientHomestayType.setDesc(adminConvenientHomestayTypeRequest.getDescType());
        ConvenientHomestayType convenientHomestayType1 = adminConvenientHomestayTypeRespository.save(convenientHomestayType);
        return convenientHomestayType1;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }


}
