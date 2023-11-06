package com.example.demo.cors.customer.services.impl;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerImgCommentRequest;
import com.example.demo.cors.customer.repository.CustomerImgCommentRepository;
import com.example.demo.cors.customer.services.CustomerImgCommentService;
import com.example.demo.entities.ImgComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerImgCommentServiceImpl implements CustomerImgCommentService {

    @Autowired
    private CustomerImgCommentRepository customerImgCommentRepository;

    @Override
    public PageableObject<ImgComment> getImgCommentByCommentId(CustomerImgCommentRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<ImgComment> res = customerImgCommentRepository.findByCommentId(pageable, request.getCommentId());
        return new PageableObject<>(res);
    }

}
