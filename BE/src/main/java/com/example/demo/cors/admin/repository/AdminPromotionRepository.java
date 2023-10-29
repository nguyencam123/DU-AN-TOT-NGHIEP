package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.request.AdminPromotionRequest;
import com.example.demo.cors.admin.model.response.AdminPromotionResponse;
import com.example.demo.repositories.PromotionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminPromotionRepository extends PromotionRepository {

    @Query(value = """
                SELECT [type],[value],[end_date],[start_date],[id],[name]
                  FROM [dbo].[promotion]
                """,nativeQuery = true)
    Page<AdminPromotionResponse> getAll(Pageable pageable, @Param("request") AdminPromotionRequest request);

}
