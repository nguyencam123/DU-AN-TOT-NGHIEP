package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.cors.customer.services.CustomerHomestayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/search")
    public ResponseObject searchHomestay(CustomerHomestayRequest request) {
        return new ResponseObject(customerHomestayService.searchHomestay(request));
    }

    @GetMapping("/getOne")
    public ResponseObject getHomestayById(CustomerHomestayRequest request) {
        return new ResponseObject(customerHomestayService.getHomestayById(request));
    }

    @GetMapping("/getByAddress")
    public ResponseObject getHomestayByAddress(CustomerHomestayRequest request) {
        return new ResponseObject(customerHomestayService.getHomestayByAddress(request));
    }

    @GetMapping("/search123")
    public ResponseObject search(CustomerHomestayRequest request) {
        return new ResponseObject(customerHomestayService.search(request));
    }

}
