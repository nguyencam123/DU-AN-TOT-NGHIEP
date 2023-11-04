package com.example.demo.cors.homestayowner.controller;


import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.service.HomestayOwnerRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/region")
public class HomestayOwnerRegionController {

    @Autowired
    private HomestayOwnerRegionService homestayOwnerRegionService;

    @GetMapping("")
    public ResponseObject getRegion() {
        return new ResponseObject(homestayOwnerRegionService.getRegion());
    }
}
