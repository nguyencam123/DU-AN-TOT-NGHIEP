package com.example.demo.controllers;

import com.example.demo.entities.Comment;
import com.example.demo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public List<Comment> getAll() {
        return commentService.getAll();
    }
}
