package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.request.AdminCommentRequest;
import com.example.demo.entities.Comment;
import com.example.demo.repositories.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCommentRepository extends CommentRepository {
    @Query(value = """
            SELECT c.*
            FROM     dbo.comment c INNER JOIN
            dbo.homestay h ON c.homestay_id = h.id
            WHERE h.id = :#{#request.homestayId}
            """, nativeQuery = true)
    Page<Comment> getAllComment(Pageable pageable, AdminCommentRequest request);
}
