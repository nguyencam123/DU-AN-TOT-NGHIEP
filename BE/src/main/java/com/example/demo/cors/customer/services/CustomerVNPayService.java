package com.example.demo.cors.customer.services;

import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.entities.Booking;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public interface CustomerVNPayService {

    Booking saveBooking(CustomerBookingRequest request);

    String customerVNPay(CustomerBookingRequest customerBookingRequest, HttpServletRequest request) throws UnsupportedEncodingException;

    Boolean orderReturn(HttpServletRequest request);


}
