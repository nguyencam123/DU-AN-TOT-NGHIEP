package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerPasswordRequest;
import com.example.demo.cors.customer.services.CustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/change-pass")
public class CustomerPassController {

    @Autowired
    private CustomerLoginService changePassword;

    @PostMapping("/changePassword")
    public ResponseObject changePassword(@RequestBody CustomerPasswordRequest request, Principal connecteUser) {
        return new ResponseObject(changePassword.changePassword(request, connecteUser));
    }

}
