package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.service.HomestayOwnerPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/promotion")
public class HomestayOwnerPromotionController {

    @Autowired
    private HomestayOwnerPromotionService homestayOwnerPromotionService;

    @GetMapping("")
    public ResponseObject getPromotion() {
        return new ResponseObject(homestayOwnerPromotionService.getPromotion());
    }
}
