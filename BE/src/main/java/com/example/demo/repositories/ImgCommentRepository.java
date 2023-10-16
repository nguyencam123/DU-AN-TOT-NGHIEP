package com.example.demo.repositories;

import com.example.demo.entities.ImgComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ImgCommentRepository.NAME)
public interface ImgCommentRepository extends JpaRepository<ImgComment, String> {
    public static final String NAME = "BaseImgCommentRepository";
}
