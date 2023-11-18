package com.example.demo.cors.admin.controller;

import com.example.demo.cors.admin.model.request.AdminBookingRequest;
import com.example.demo.cors.admin.model.request.AdminStatisticalRequest;
import com.example.demo.cors.admin.services.impl.AdminStatisticalServiceImpl;
import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerStatisticalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v3/statistical")
public class AdminStatisticalController {

    @Autowired
    private AdminStatisticalServiceImpl adminStatisticalService;

    @GetMapping("/month-year")
    public ResponseObject getAll( AdminStatisticalRequest adminStatisticalRequest) {
        return new ResponseObject(adminStatisticalService.getThongKe(adminStatisticalRequest));
    }

    @GetMapping("/year")
    public ResponseObject getYear(final AdminStatisticalRequest request) {
        return new ResponseObject(adminStatisticalService.getAllStatisticalForAllMonthsInYear(request));
    }

}
