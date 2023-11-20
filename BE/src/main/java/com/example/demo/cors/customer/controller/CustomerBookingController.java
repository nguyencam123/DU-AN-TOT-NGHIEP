package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.model.response.CustomerVNPayResponse;
import com.example.demo.cors.customer.services.CustomerBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/booking")
public class CustomerBookingController {

    @Autowired
    private CustomerBookingService customerBookingService;

    @GetMapping()
    public ResponseObject getBookingByUser(CustomerBookingRequest customerBookingRequest) {
        return new ResponseObject(customerBookingService.getBookingByUser(customerBookingRequest));
    }

    @PostMapping("/create")
    public ResponseObject createBooking(@RequestBody CustomerBookingRequest customerBookingRequest) {
        return new ResponseObject(customerBookingService.saveBooking(customerBookingRequest));
    }

}
