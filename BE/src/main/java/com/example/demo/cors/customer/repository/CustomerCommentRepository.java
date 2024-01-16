package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerCommentRequest;
import com.example.demo.entities.Comment;
import com.example.demo.repositories.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCommentRepository extends CommentRepository {

    Page<Comment> findByHomestayId(Pageable pageable, String homestayId);

    @Query(value = """
            SELECT COUNT(*) FROM comment a
            WHERE a.homestay_id =:#{#customerCommentRequest.homestayId} AND (a.point BETWEEN :#{#customerCommentRequest.pointMin} AND :#{#customerCommentRequest.pointMax})
            """, nativeQuery = true)
    Integer getNumberOfReviewers(CustomerCommentRequest customerCommentRequest);

    @Query(value = """
            SELECT AVG(a.point) AS 'Average_point' FROM comment a
            WHERE a.homestay_id =:#{#customerCommentRequest.homestayId}
            """, nativeQuery = true)
    Double getAvgPoint(CustomerCommentRequest customerCommentRequest);

    @Query(value = """
                Select * from Comment Where user_id=:idUser
            """, nativeQuery = true)
    Page<Comment> commentByUserId(Pageable pageable, String idUser);

}
