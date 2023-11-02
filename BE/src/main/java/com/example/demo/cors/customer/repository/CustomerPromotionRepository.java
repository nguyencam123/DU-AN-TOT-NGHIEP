package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.response.CustomerPromotionResponse;
import com.example.demo.repositories.PromotionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPromotionRepository extends PromotionRepository {

    @Query(value = """
            SELECT a.id, a.name, a.type, a.value, a.start_date, a.end_date FROM promotion a
            """, nativeQuery = true)
    Page<CustomerPromotionResponse> getAllPromotion(Pageable pageable);

}
