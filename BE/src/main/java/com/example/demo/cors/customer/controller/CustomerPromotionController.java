package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerPromotionRequest;
import com.example.demo.cors.customer.services.CustomerPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/promotion")
public class CustomerPromotionController {

    @Autowired
    private CustomerPromotionService customerPromotionService;

    @GetMapping()
    public ResponseObject getAllPromotion(CustomerPromotionRequest request) {
        return new ResponseObject(customerPromotionService.getAllPromotion(request));
    }

}
