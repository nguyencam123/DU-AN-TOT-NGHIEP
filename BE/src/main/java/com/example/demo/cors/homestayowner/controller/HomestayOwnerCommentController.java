package com.example.demo.cors.homestayowner.controller;

import com.example.demo.cors.common.base.ResponseObject;
import com.example.demo.cors.homestayowner.model.request.HomestayOwnerCommentRequest;
import com.example.demo.cors.homestayowner.service.HomestayOwnerCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2/comment")
public class HomestayOwnerCommentController {

    @Autowired
    private HomestayOwnerCommentService homestayOwnerCommitService;

    @GetMapping("")
    public ResponseObject getAllComment(@RequestParam("idHomestay") String idHomestay, HomestayOwnerCommentRequest request) {
        return new ResponseObject(homestayOwnerCommitService.getComment(idHomestay,request));
    }

    @PostMapping("/add")
    public ResponseObject addComment(final HomestayOwnerCommentRequest request) throws IOException {
        return new ResponseObject(homestayOwnerCommitService.addComment(request));
    }

}
