package com.example.demo.cors.admin.controller;

import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.admin.model.request.AdminRequest;
import com.example.demo.cors.admin.services.AdminApproveHomestayService;
import com.example.demo.cors.admin.services.AdminHomestayService;
import com.example.demo.cors.common.base.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v3/homestay")
public class AdminHomestayController {

    @Autowired
    private AdminHomestayService adminHomestayService;
    @Autowired
    private AdminApproveHomestayService adminApproveHomestayService;

    @GetMapping()
    public ResponseObject getAll(AdminHomestayRequest adminHomestayRequest) {
        return new ResponseObject(adminHomestayService.getAll(adminHomestayRequest));
    }

    @PutMapping("/approve/{id}")
    public ResponseObject adminApproveHomestay(@PathVariable("id") String id, @RequestBody AdminRequest request) {
        return new ResponseObject(adminApproveHomestayService.adminApproveHomestay(id, request));
    }

}
