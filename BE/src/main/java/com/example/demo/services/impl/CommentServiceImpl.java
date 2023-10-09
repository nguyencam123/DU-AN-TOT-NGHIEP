package com.example.demo.services.impl;

import com.example.demo.entities.Comment;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAll(){
        return commentRepository.findAll();
    }
}
