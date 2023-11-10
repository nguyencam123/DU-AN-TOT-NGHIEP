package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminConvenientHomestayRequest;
import com.example.demo.cors.admin.model.response.AdminConvenientHomestayResponse;
import com.example.demo.cors.admin.repository.AdminConvenientHomestayRepository;
import com.example.demo.cors.admin.repository.AdminConvenientHomestayTypeRespository;
import com.example.demo.cors.admin.services.AdminConvenientHomestayService;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.ConvenientHomestay;
import com.example.demo.entities.ConvenientHomestayType;
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
    public PageableObject<AdminConvenientHomestayResponse> getAllConvenient(AdminConvenientHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<AdminConvenientHomestayResponse> res = adminConvenientHomestayRepository.getAllConvenient(pageable);
        return new PageableObject<>(res);
    }

    @Override
    public ConvenientHomestay addConvenientHomestay( AdminConvenientHomestayRequest adminConvenientHomestayRequest){
        ConvenientHomestayType convenientHomestayType = new ConvenientHomestayType();
        convenientHomestayType.setName(adminConvenientHomestayRequest.getNameType());
        convenientHomestayType.setDesc(adminConvenientHomestayRequest.getDescType());
        ConvenientHomestayType convenientHomestayType1 = adminConvenientHomestayTypeRespository.save(convenientHomestayType);
        ConvenientHomestay convenientHomestay = new ConvenientHomestay();
        convenientHomestay.setName(adminConvenientHomestayRequest.getName());
        convenientHomestay.setDesc(adminConvenientHomestayRequest.getDesc());
        convenientHomestay.setConvenientHomestayType(convenientHomestayType1);
        ConvenientHomestay convenientHomestay1 = adminConvenientHomestayRepository.save(convenientHomestay);
        return convenientHomestay1;
    }


}
