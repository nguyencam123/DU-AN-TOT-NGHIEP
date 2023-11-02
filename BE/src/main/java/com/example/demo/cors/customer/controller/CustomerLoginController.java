package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerLoginRequest;
import com.example.demo.cors.customer.model.request.CustomerRequest;
import com.example.demo.cors.customer.model.request.CustomerUserPasswordRequest;
import com.example.demo.cors.customer.services.CustomerLoginService;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerOwnerHomestayRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerUsenamePasswordRequest;
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

    @PostMapping("/authenticate")
    public ResponseObject authenticate(@RequestBody CustomerUserPasswordRequest request){
        return new ResponseObject(customerLoginService.CustomerAuthenticate(request));
    }

    @PostMapping("/registers")
    public ResponseObject registers(@RequestBody CustomerRequest request){
        return new ResponseObject(customerLoginService.CustomerRegister(request));
    }

}
