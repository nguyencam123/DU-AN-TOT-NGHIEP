package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminRequest;
import com.example.demo.cors.admin.model.request.AdminUserPasswordRequest;
import com.example.demo.cors.admin.model.response.AdminAuthenticationReponse;
import com.example.demo.cors.admin.model.response.AdminLoginResponse;
import com.example.demo.cors.admin.model.request.AdminLoginRequest;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerAuthenticationReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerOwnerHomestayRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerUsenamePasswordRequest;

public interface AdminLoginService {

    AdminLoginResponse getAdLogin(AdminLoginRequest adminLoginRequest);

    AdminAuthenticationReponse register(AdminRequest request);

    AdminAuthenticationReponse authenticate(AdminUserPasswordRequest request);


}
