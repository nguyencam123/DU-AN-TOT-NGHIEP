package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerStatisticalRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerStatisticalServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/statictical")
public class HomestayOwnerStatisticalController {

    @Autowired
    private HomestayOwnerStatisticalServie homestayOwnerStatisticalServie;

    @GetMapping()
    public ResponseObject getAll(@RequestParam("id") String id) {
        return new ResponseObject(homestayOwnerStatisticalServie.getStatistical(id));
    }

    @GetMapping("/month-and-year")
    public ResponseObject getMonthAndYear(final HomestayOwnerStatisticalRequest request) {
        return new ResponseObject(homestayOwnerStatisticalServie.getStatisticalbyMonthAndYear(request));
    }

    @GetMapping("/year")
    public ResponseObject getYear(final HomestayOwnerStatisticalRequest request) {
        return new ResponseObject(homestayOwnerStatisticalServie.getAllStatisticalForAllMonthsInYear(request));
    }

}
