package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.services.CustomerConvenientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/convenient-homestay-type")
public class CustomerConvenientHomestayTypeController {

    @Autowired
    private CustomerConvenientTypeService customerConvenientTypeService;

    @GetMapping()
    private ResponseObject getAll() {
        return new ResponseObject(customerConvenientTypeService.getAll());
    }
}
