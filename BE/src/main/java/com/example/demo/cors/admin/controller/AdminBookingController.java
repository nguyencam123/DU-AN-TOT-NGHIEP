package com.example.demo.cors.admin.controller;

import com.example.demo.cors.admin.model.request.AdminBookingRequest;
import com.example.demo.cors.admin.services.AdminBookingService;
import com.example.demo.cors.common.base.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v3/booking")
public class AdminBookingController {

    @Autowired
    private AdminBookingService adminBookingService;

    @GetMapping("")
    public ResponseObject getAll(AdminBookingRequest request) {
        return new ResponseObject(adminBookingService.getAllBooking(request));
    }

}
