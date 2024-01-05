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
            SELECT ROW_NUMBER() OVER(ORDER BY d.created_date DESC) AS stt,d.*
            FROM     dbo.comment d  
            JOIN dbo.homestay h ON d.homestay_id = h.id
            JOIN dbo.[user] u ON d.user_id = u.id
            WHERE ( ( :#{#request.homestayId} IS NULL OR h.id = :#{#request.homestayId} )
            AND ( :#{#request.userName} IS NULL OR :#{#request.userName} LIKE '' OR  u.name LIKE %:#{#request.userName}% )
            AND (:#{#request.homestayName} IS NULL OR :#{#request.homestayName} LIKE '' OR  h.name LIKE %:#{#request.homestayName}% )
            AND (:#{#request.userId} IS NULL OR u.id = :#{#request.userId}))
            """, nativeQuery = true)
    Page<Comment> getAllComment(Pageable pageable, AdminCommentRequest request);
}
