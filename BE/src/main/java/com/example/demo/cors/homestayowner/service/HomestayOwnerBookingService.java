package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerBookingReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerBookingRequest;

public interface HomestayOwnerBookingService {

    PageableObject<HomestayOwnerBookingReponse> getBookingByHomestay(String id,HomestayOwnerBookingRequest homestayOwnerBookingRequest);

}
