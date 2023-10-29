package com.example.demo.cors.admin.controller;

import com.example.demo.cors.admin.model.request.AdminPromotionRequest;
import com.example.demo.cors.admin.services.AdminPromotionService;
import com.example.demo.cors.common.base.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v3/promotion")
public class AdminPromotionController {

    @Autowired
    private AdminPromotionService adminPromotionService;

    @GetMapping("")
    public ResponseObject getAll(AdminPromotionRequest adminPromotionRequest){
        return new ResponseObject(adminPromotionService.getAll(adminPromotionRequest));
    }
}
