package com.example.demo.controllers;

import com.example.demo.entities.Admin;
import com.example.demo.request.AdminRequest;
import com.example.demo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public List<Admin> getAll(){
        return adminService.getAll();
    }

    @PostMapping("/login")
    public Admin getAdmin(@RequestBody AdminRequest adminRequest){
        return adminService.getAdmin(adminRequest);
    }
}
