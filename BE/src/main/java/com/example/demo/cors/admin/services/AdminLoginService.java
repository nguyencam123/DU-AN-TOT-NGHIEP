package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.response.AdminLoginResponse;
import com.example.demo.cors.admin.model.request.AdminLoginRequest;

public interface AdminLoginService {

    AdminLoginResponse getAdLogin(AdminLoginRequest adminLoginRequest);

}
