package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.model.response.CustomerVNPayResponse;
import com.example.demo.entities.Booking;

public interface CustomerBookingService {

    PageableObject<Booking> getBookingByUser(CustomerBookingRequest request);

    Booking saveBooking(CustomerBookingRequest request);

}
