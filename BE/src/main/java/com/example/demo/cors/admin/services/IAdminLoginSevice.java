package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.reponse.AdminLoginReponsi;
import com.example.demo.cors.admin.model.request.AdminLoginRequest;

public interface IAdminLoginSevice {
    AdminLoginReponsi getAdLogin(AdminLoginRequest adminLoginRequest);
}
