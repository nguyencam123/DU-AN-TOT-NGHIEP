package com.example.demo.cors.customer.services;

import com.example.demo.cors.customer.model.request.CustomerVNPayRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface CustomerVNPayService {

    String customerVNPay(CustomerVNPayRequest customerVNPayRequest, HttpServletRequest request) throws UnsupportedEncodingException;

    Integer orderReturn(HttpServletRequest request);

}
