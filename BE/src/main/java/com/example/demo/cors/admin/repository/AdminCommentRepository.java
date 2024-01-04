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
            JOIN dbo.[user] u ON c.user_id = u.id
            WHERE h.id = :#{#request.homestayId}
            AND ( :#{#request.userName} IS NULL OR :#{#request.userName} LIKE '' OR  u.name LIKE %:#{#request.userName}% )
            """, nativeQuery = true)
    Page<Comment> getAllComment(Pageable pageable, AdminCommentRequest request);
}
