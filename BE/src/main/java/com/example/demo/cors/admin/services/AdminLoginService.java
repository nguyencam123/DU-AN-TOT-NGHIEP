package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminLoginRequest;
import com.example.demo.cors.admin.model.request.AdminPassRequest;
import com.example.demo.cors.admin.model.request.AdminRequest;
import com.example.demo.cors.admin.model.request.AdminUserPasswordRequest;
import com.example.demo.cors.admin.model.response.AdminAuthenticationReponse;
import com.example.demo.cors.admin.model.response.AdminLoginResponse;

import java.security.Principal;

public interface AdminLoginService {

    AdminLoginResponse getAdLogin(AdminLoginRequest adminLoginRequest);

    AdminAuthenticationReponse register(AdminRequest request);

    AdminAuthenticationReponse authenticate(AdminUserPasswordRequest request);

    AdminAuthenticationReponse changePassword(AdminPassRequest request, Principal connecteUser);

}
