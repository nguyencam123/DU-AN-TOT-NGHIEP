package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.cors.customer.services.CustomerHomestayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/homestay")
public class CustomerHomestayController {

    @Autowired
    private CustomerHomestayService customerHomestayService;

    @GetMapping()
    public ResponseObject getListHotel(CustomerHomestayRequest request) {
        return new ResponseObject(customerHomestayService.getListHomestay(request));
    }

    @GetMapping("/getByConvenient")
    public ResponseObject getHomestayByConvenientId(CustomerHomestayRequest request) {
        return new ResponseObject(customerHomestayService.getHomestayByConvenientId(request));
    }

    @GetMapping("/getByProvince")
    public ResponseObject getHomestayByProvince(CustomerHomestayRequest request) {
        return new ResponseObject(customerHomestayService.getHomestayByProvince(request));
    }

    @GetMapping("/getByRegion")
    public ResponseObject getHomestayByRegion(CustomerHomestayRequest request) {
        return new ResponseObject(customerHomestayService.getHomestayByRegion(request));
    }

}
