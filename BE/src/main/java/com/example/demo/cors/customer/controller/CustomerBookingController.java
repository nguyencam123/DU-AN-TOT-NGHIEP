package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.services.CustomerBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/booking")
public class CustomerBookingController {

    @Autowired
    private CustomerBookingService customerBookingService;

    @GetMapping()
    public ResponseObject getBookingByUser(CustomerBookingRequest customerBookingRequest) {
        return new ResponseObject(customerBookingService.getBookingByUser(customerBookingRequest));
    }

    @PostMapping("/create")
    public ResponseObject createBooking(@RequestBody CustomerBookingRequest customerBookingRequest) {
        return new ResponseObject(customerBookingService.saveBooking(customerBookingRequest));
    }

    @PutMapping("/update")
    private ResponseObject updateBooking(CustomerBookingRequest customerBookingRequest) {
        return new ResponseObject(customerBookingService.updateBooking(customerBookingRequest));
    }

    @PutMapping("/cancel/{bookingId}")
    public ResponseObject cancelBooking(@PathVariable("bookingId") String bookingId, @Valid @RequestBody CustomerBookingRequest customerBookingRequest, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            return new ResponseObject(list);
        } else {
            return new ResponseObject((customerBookingService.cancel(bookingId, customerBookingRequest)));
        }
    }

}
