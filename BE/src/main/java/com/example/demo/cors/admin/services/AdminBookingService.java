package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminBookingByHomestayRequest;
import com.example.demo.cors.admin.model.request.AdminBookingRequest;
import com.example.demo.cors.admin.model.response.AdminBookingResponse;
import com.example.demo.cors.common.base.PageableObject;

public interface AdminBookingService {

    PageableObject<AdminBookingResponse> getAllBooking(AdminBookingRequest request);

    PageableObject<AdminBookingResponse> getAllBookingByHomestay(AdminBookingByHomestayRequest request);

}
