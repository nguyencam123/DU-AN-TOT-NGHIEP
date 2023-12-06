package com.example.demo.cors.homestayowner.service;

import com.example.demo.cors.common.base.PageableObject;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerCommentRequest;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerDeleteCommentRequest;
import com.example.demo.entities.Comment;

import java.io.IOException;

public interface HomestayOwnerCommentService {

    PageableObject<Comment> getComment(String idHomestay, HomestayOwnerCommentRequest homestayOwnerCommentRequest);

    Comment addComment(HomestayOwnerCommentRequest request) throws IOException;

    Comment delete(HomestayOwnerDeleteCommentRequest request);
}
