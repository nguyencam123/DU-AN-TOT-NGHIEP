package com.example.demo.cors.admin.services;

import com.example.demo.cors.admin.model.request.AdminCommentRequest;
import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Homestay;

public interface AdminCommentService {

    PageableObject<Comment> getAllCommentHomestay(AdminCommentRequest request);

    Comment deleteComment (AdminCommentRequest request);
}
