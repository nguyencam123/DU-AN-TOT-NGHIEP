package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerCommentAddRequest;
import com.example.demo.cors.customer.model.request.CustomerCommentRequest;
import com.example.demo.entities.Comment;

import java.io.IOException;

public interface CustomerCommentService {

    PageableObject<Comment> getCommentByHomestayId(CustomerCommentRequest request);

    Integer getNumberOfReviewers(CustomerCommentRequest request);

    Double getAvgPoint(CustomerCommentRequest request);

    Comment addComment(CustomerCommentAddRequest request) throws IOException;

}
