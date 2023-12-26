package com.example.demo.cors.admin.services.impl;

import com.example.demo.cors.admin.model.request.AdminCommentRequest;
import com.example.demo.cors.admin.repository.AdminCommentRepository;
import com.example.demo.cors.admin.repository.AdminHomestayRepository;
import com.example.demo.cors.admin.repository.AdminImgCommentRepository;
import com.example.demo.cors.admin.services.AdminCommentService;
import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Homestay;
import com.example.demo.entities.ImgComment;
import com.example.demo.infrastructure.contant.Message;
import com.example.demo.infrastructure.exception.rest.RestApiException;
import com.example.demo.repositories.ImgCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminCommetServiceImpl implements AdminCommentService {

    @Autowired
    private AdminCommentRepository adminCommentRepository;

    @Autowired
    private AdminImgCommentRepository adminImgCommentRepository;

    @Override
    public PageableObject<Comment> getAllCommentHomestay(AdminCommentRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Comment> comments = adminCommentRepository.getAllComment(pageable, request);
        return new PageableObject<>(comments);
    }

    @Override
    public Comment deleteComment(AdminCommentRequest request) {

        Comment comment = adminCommentRepository.findById(request.getCommentId()).orElse(null);

        if (comment != null) {
            ImgComment imgComment = adminImgCommentRepository.findById(comment.getId()).orElse(null);

            if (imgComment != null) {
                adminImgCommentRepository.delete(imgComment);
            }

            adminCommentRepository.delete(comment);
            return comment;
        } else {
            return null;
        }
    }

}
