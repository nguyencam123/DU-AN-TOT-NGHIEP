package com.example.demo.cors.admin.controller;

import com.example.demo.cors.admin.model.request.AdminBookingRequest;
import com.example.demo.cors.admin.model.request.AdminStatisticalRequest;
import com.example.demo.cors.admin.model.request.AdminStatisticalTop5Request;
import com.example.demo.cors.admin.services.impl.AdminStatisticalServiceImpl;
import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerStatisticalRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerTop5StatisticalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v3/statistical")
public class AdminStatisticalController {

    @Autowired
    private AdminStatisticalServiceImpl adminStatisticalService;

    @GetMapping()
    public ResponseObject getAll(@RequestParam("id") String id) {
        return new ResponseObject(adminStatisticalService.getStatistical(id));
    }

    @GetMapping("/month-and-year")
    public ResponseObject getMonthAndYear(final AdminStatisticalRequest request) {
        return new ResponseObject(adminStatisticalService.getStatisticalbyMonthAndYear(request));
    }

    @GetMapping("/year")
    public ResponseObject getYear(final AdminStatisticalRequest request) {
        return new ResponseObject(adminStatisticalService.getAllStatisticalForAllMonthsInYear(request));
    }

    @GetMapping("/top5")
    public ResponseObject getTop5(final AdminStatisticalTop5Request request) {
        return new ResponseObject(adminStatisticalService.getTop5HomestayInYear(request));
    }

}
