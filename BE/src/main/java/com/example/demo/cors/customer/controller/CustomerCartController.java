package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerBookingRequest;
import com.example.demo.cors.customer.model.request.CustomerCartRequest;
import com.example.demo.cors.customer.services.CustomerCartDetailService;
import com.example.demo.cors.customer.services.CustomerCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerCartController {

    @Autowired
    private CustomerCartService customerCartService;
    @Autowired
    private CustomerCartDetailService customerCartDetailService;

    @GetMapping("")
    public ResponseObject getAllHomestayInCart(CustomerCartRequest request) {
        return new ResponseObject(customerCartService.getAllHomestayInCart(request));
    }

    @PostMapping("/add")
    public ResponseObject addCart(@RequestBody CustomerCartRequest request) {
        return new ResponseObject(customerCartService.addCart(request));
    }

    @DeleteMapping("/delete")
    public ResponseObject deleteCartDetail(@RequestParam("idCartDetail") String idCartDetail) {
        return new ResponseObject(customerCartDetailService.deleteCartDetail(idCartDetail));
    }

    @DeleteMapping("/delete-all")
    public ResponseObject deleteAllCartDetail(@RequestParam("userId") String userId) {
        return new ResponseObject(customerCartDetailService.deleteAllCartDetail(userId));
    }

    @GetMapping("/check-available")
    public ResponseObject checkAvailable(CustomerBookingRequest request) {
        return new ResponseObject((customerCartService.getOne(request)));
    }

}
