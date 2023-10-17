package com.example.demo.cors.admin.controller;

import com.example.demo.cors.admin.model.reponse.AdminLoginReponsi;
import com.example.demo.cors.admin.model.request.AdminLoginRequest;
import com.example.demo.cors.admin.services.IAdminLoginSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v3/login")
public class AdminLoginController {
    @Autowired
    private IAdminLoginSevice iAdminLoginSevice ;

    @PostMapping("")
    public AdminLoginReponsi getAdLogin(@RequestBody AdminLoginRequest adminLoginRequest){
        return iAdminLoginSevice.getAdLogin(adminLoginRequest);
    }
}
