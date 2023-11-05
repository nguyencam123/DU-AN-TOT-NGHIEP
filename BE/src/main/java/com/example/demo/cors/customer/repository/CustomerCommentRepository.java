package com.example.demo.cors.customer.repository;

import com.example.demo.entities.Comment;
import com.example.demo.repositories.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCommentRepository extends CommentRepository {

    Page<Comment> findByHomestayId(Pageable pageable, String homestayId);

}
