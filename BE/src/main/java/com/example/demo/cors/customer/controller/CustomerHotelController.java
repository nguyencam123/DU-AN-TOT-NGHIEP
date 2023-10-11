package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerHotelRequest;
import com.example.demo.cors.customer.services.CustomerHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerHotelController {

    @Autowired
    private CustomerHotelService hotelService;

    @GetMapping("/list-hotel")
    public ResponseObject getListHotel(CustomerHotelRequest request) {
        return new ResponseObject(hotelService.getListHotel(request));
    }

}
