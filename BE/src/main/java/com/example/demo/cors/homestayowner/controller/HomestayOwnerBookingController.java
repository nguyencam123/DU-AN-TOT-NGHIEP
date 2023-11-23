package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerBookingRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/booking")
@PreAuthorize("hasRole('OWNER')")
public class HomestayOwnerBookingController {

    @Autowired
    private HomestayOwnerBookingService homestayOwnerBookingService;

    @GetMapping("/byid")
    @PreAuthorize("hasAuthority('owner:read')")
    public ResponseObject getHomestayByConvenient(@RequestParam("id") String id, HomestayOwnerBookingRequest homestayOwnerBookingRequest) {
        return new ResponseObject(homestayOwnerBookingService.getBookingByHomestay(id, homestayOwnerBookingRequest));
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('owner:read')")
    public ResponseObject getAll(final HomestayOwnerBookingRequest request) {
        return new ResponseObject(homestayOwnerBookingService.getAllBooking(request));
    }

}
