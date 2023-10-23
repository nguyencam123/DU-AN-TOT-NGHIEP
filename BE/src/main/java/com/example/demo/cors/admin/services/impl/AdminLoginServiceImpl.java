package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.response.AdminLoginResponse;
import com.example.demo.cors.admin.model.request.AdminLoginRequest;
import com.example.demo.cors.admin.repository.AdminLoginRepository;
import com.example.demo.cors.admin.services.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Autowired
    private AdminLoginRepository adminLoginRepository;

    @Override
    public AdminLoginResponse getAdLogin(AdminLoginRequest adminLoginRequest) {
        return adminLoginRepository.getLogin(adminLoginRequest);
    }
}
