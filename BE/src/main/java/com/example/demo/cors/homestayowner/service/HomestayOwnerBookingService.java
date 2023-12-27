package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerBookingRequest;
import com.example.demo.entities.Booking;

public interface HomestayOwnerBookingService {

    PageableObject<Booking> getBookingByHomestay(String id, HomestayOwnerBookingRequest homestayOwnerBookingRequest);

    PageableObject<Booking> getAllBooking(HomestayOwnerBookingRequest request);

    PageableObject<Booking> getBookingByYearAndMonth(HomestayOwnerBookingRequest request);

}
