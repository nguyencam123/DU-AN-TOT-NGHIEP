package com.example.demo.cors.homestayowner.repository;

import com.example.demo.entities.Promotion;
import com.example.demo.repositories.PromotionRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomestayOwnerPromotionRepository extends PromotionRepository {

    @Query(value = "select * from promotion where owner_id=:id",nativeQuery = true)
    List<Promotion> getAllPromotion(String id);

    Boolean existsByName(String name);

}
