package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerLoginRequest;
import com.example.demo.cors.customer.services.CustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/login")
public class CustomerLoginController {

    @Autowired
    private CustomerLoginService customerLoginService;

    @PostMapping()
    public ResponseObject getCustomerLogin(@RequestBody CustomerLoginRequest customerLoginRequest) {
        return new ResponseObject(customerLoginService.getCustomerLogin(customerLoginRequest));
    }

}