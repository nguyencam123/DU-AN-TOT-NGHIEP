package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerHomestayRequest;
import com.example.demo.cors.customer.services.CustomerHomestayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getOne")
    public ResponseObject getHomestayById(CustomerHomestayRequest request) {
        return new ResponseObject(customerHomestayService.getHomestayById(request));
    }

    @GetMapping("/search")
    public ResponseObject search(CustomerHomestayRequest request) {
        return new ResponseObject(customerHomestayService.findAllBetweenDate(request));
    }

    @GetMapping("get-user-by-token")
    public ResponseObject getOwnerByToken(@RequestParam("token") String token) {
        return new ResponseObject(customerHomestayService.getCustomerByToken(token));
    }

}
