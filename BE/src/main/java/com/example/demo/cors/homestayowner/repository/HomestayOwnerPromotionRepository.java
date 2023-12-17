package com.example.demo.cors.homestayowner.repository;

import com.example.demo.cors.homestayowner.model.request.HomestayOwnerPromotionSearchRequest;
import com.example.demo.entities.Promotion;
import com.example.demo.repositories.PromotionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomestayOwnerPromotionRepository extends PromotionRepository {

    @Query(value = "select * from promotion where owner_id=:id",nativeQuery = true)
    List<Promotion> getAllPromotion(String id);

    Boolean existsByName(String name);

    @Query(value = """
            SELECT *
            FROM promotion a
            WHERE a.owner_id = :#{#request.idOwner}
            AND (
            (a.name LIKE %:#{#request.name}% OR :#{#request.name} IS NULL OR a.name like '')
            AND (:#{#request.status} is null or a.status_promotion=:#{#request.status})
            )
    """,nativeQuery = true)
    Page<Promotion> getBookingByNameAndStatus(@Param("request") HomestayOwnerPromotionSearchRequest request, Pageable pageable);

}
