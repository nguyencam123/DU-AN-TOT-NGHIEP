package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerCommentRequest;
import com.example.demo.cors.customer.repository.CustomerCommentRepository;
import com.example.demo.cors.customer.services.CustomerCommentService;
import com.example.demo.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerCommentServiceImpl implements CustomerCommentService {

    @Autowired
    private CustomerCommentRepository customerCommentRepository;

    @Override
    public PageableObject<Comment> getCommentByHomestayId(CustomerCommentRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Comment> res = customerCommentRepository.findByHomestayId(pageable, request.getHomestayId());
        return new PageableObject<>(res);
    }

    @Override
    public Integer getNumberOfReviewers(CustomerCommentRequest request) {
        return customerCommentRepository.getNumberOfReviewers(request);
    }

    @Override
    public Double getAvgPoint(CustomerCommentRequest request) {
        return customerCommentRepository.getAvgPoint(request);
    }

}
