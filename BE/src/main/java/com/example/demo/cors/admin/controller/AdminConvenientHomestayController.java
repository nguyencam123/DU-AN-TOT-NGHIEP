package com.example.demo.cors.admin.controller;

import com.example.demo.cors.admin.model.request.AdminConvenientHomestayRequest;
import com.example.demo.cors.admin.services.AdminConvenientHomestayService;
import com.example.demo.cors.common.base.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @PostMapping("/add")
    public ResponseObject addConvenient( AdminConvenientHomestayRequest adminConvenientHomestayRequest) {
        return new ResponseObject(adminConvenientHomestayService.addConvenientHomestay(adminConvenientHomestayRequest));
    }

}
