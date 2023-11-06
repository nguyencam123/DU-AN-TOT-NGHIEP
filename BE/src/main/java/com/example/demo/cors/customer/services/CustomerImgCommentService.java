package com.example.demo.cors.customer.services;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.customer.model.request.CustomerImgCommentRequest;
import com.example.demo.entities.ImgComment;

public interface CustomerImgCommentService {

    PageableObject<ImgComment> getImgCommentByCommentId(CustomerImgCommentRequest request);

}
