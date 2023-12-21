package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerForgetRequest;
import com.example.demo.cors.customer.model.request.CustomerRequest;
import com.example.demo.cors.customer.model.request.CustomerUserPassRequest;
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

    @PostMapping("/authenticate")
    public ResponseObject authenticate(@RequestBody CustomerUserPassRequest request) {
        return new ResponseObject(customerLoginService.CustomerAuthenticate(request));
    }

    @PostMapping("/registers")
    public ResponseObject registers(@RequestBody CustomerRequest request) {
        return new ResponseObject(customerLoginService.CustomerRegister(request));
    }

    @PostMapping("/forget-password")
    public ResponseObject forgetPassword(final CustomerForgetRequest request) {
        customerLoginService.sendResetPasswordEmail(request);
        return new ResponseObject("Đã gửi mật khẩu mới về Email");
    }


}
