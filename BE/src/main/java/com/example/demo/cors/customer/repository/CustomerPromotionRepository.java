package com.example.demo.cors.customer.repository;

import com.example.demo.repositories.PromotionRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface CustomerPromotionRepository extends PromotionRepository {
}
