package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.services.CustomerVNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseObject paymentWithVNPay(@RequestBody CustomerBookingRequest customerBookingRequest, HttpServletRequest request) {
        try {
            return new ResponseObject(customerVNPayService.customerVNPay(customerBookingRequest, request));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/booking-return")
    public ResponseObject paymentSuccess(HttpServletRequest request) {
        return new ResponseObject(customerVNPayService.orderReturn(request));
    }

}
