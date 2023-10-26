package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminConvenientHomestayRequest;
import com.example.demo.cors.admin.model.response.AdminConvenientHomestayResponse;
import com.example.demo.cors.admin.repository.AdminConvenientHomestayRepository;
import com.example.demo.cors.admin.services.AdminConvenientHomestayService;
import com.example.demo.cors.common.base.PageableObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminConvenientHomestayServiceImpl implements AdminConvenientHomestayService {

    @Autowired
    private AdminConvenientHomestayRepository adminConvenientHomestayRepository;

    @Override
    public PageableObject<AdminConvenientHomestayResponse> getAllConvenient(AdminConvenientHomestayRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<AdminConvenientHomestayResponse> res = adminConvenientHomestayRepository.getAllConvenient(pageable);
        return new PageableObject<>(res);
    }

}
