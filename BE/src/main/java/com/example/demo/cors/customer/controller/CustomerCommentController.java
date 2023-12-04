package com.example.demo.cors.customer.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.customer.model.request.CustomerCommentRequest;
import com.example.demo.cors.customer.services.CustomerCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/comment")
public class CustomerCommentController {

    @Autowired
    private CustomerCommentService customerCommentService;

    @GetMapping()
    public ResponseObject getCommentByHomestay(CustomerCommentRequest request) {
        return new ResponseObject(customerCommentService.getCommentByHomestayId(request));
    }

    @GetMapping("/number-of-reviewers")
    public ResponseObject getNumberOfReviewers(CustomerCommentRequest request) {
        return new ResponseObject(customerCommentService.getNumberOfReviewers(request));
    }

    @GetMapping("/avg-point")
    public ResponseObject getAvgPoint(CustomerCommentRequest request) {
        return new ResponseObject(customerCommentService.getAvgPoint(request));
    }

}
