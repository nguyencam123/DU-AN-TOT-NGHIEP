package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.reponse.AdminLoginReponsi;
import com.example.demo.cors.admin.model.request.AdminLoginRequest;
import com.example.demo.cors.admin.repository.AdminLoginRepository;
import com.example.demo.cors.admin.services.IAdminLoginSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginSevice implements IAdminLoginSevice {
    @Autowired
    private AdminLoginRepository adminLoginRepository;

    @Override
    public AdminLoginReponsi getAdLogin(AdminLoginRequest adminLoginRequest) {
        return adminLoginRepository.getLogin(adminLoginRequest);
    }
}
