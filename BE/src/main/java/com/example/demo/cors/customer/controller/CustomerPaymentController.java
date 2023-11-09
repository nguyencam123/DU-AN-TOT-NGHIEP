package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerVNPayRequest;
import com.example.demo.cors.customer.services.CustomerVNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/payment")
public class CustomerPaymentController {

    @Autowired
    private CustomerVNPayService customerVNPayService;

    @PostMapping("/vnpay")
    public ResponseObject paymentWithVNPay(@RequestBody CustomerVNPayRequest customerVNPayRequest, HttpServletRequest request) {
        try {
            return new ResponseObject(customerVNPayService.customerVNPay(customerVNPayRequest, request));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
