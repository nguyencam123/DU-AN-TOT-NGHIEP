package com.example.demo.cors.admin.controller;

import com.example.demo.cors.admin.model.response.AdminLoginResponse;
import com.example.demo.cors.admin.model.request.AdminLoginRequest;
import com.example.demo.cors.admin.services.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v3/login")
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminLoginService;

    @PostMapping("")
    public AdminLoginResponse getAdLogin(@RequestBody AdminLoginRequest adminLoginRequest){
        return adminLoginService.getAdLogin(adminLoginRequest);
    }
}
