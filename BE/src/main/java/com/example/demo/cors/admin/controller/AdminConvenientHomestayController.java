package com.example.demo.cors.admin.controller;

import com.example.demo.cors.admin.model.request.AdminConvenientHomestayRequest;
import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.admin.services.AdminConvenientHomestayService;
import com.example.demo.cors.common.base.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v3/convenient-homestay")
public class AdminConvenientHomestayController {

    @Autowired
    private AdminConvenientHomestayService adminConvenientHomestayService;

    @GetMapping()
    public ResponseObject getAllConvenient(AdminConvenientHomestayRequest adminConvenientHomestayRequest){
        return new ResponseObject(adminConvenientHomestayService.getAllConvenient(adminConvenientHomestayRequest));
    }

}
