package com.example.demo.cors.customer.repository;

import com.example.demo.entities.ImgComment;
import com.example.demo.repositories.ImgCommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerImgCommentRepository extends ImgCommentRepository {

    Page<ImgComment> findByCommentId(Pageable pageable, String commentId);

}
