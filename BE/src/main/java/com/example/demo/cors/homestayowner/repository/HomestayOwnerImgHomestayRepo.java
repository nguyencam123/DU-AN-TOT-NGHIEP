package com.example.demo.cors.homestayowner.repository;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerImgHomestayReponse;
import com.example.demo.repositories.ImgHomestayRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HomestayOwnerImgHomestayRepo extends ImgHomestayRepository {

    @Query(value = "Select a.img_url from img_homestay a where a.homestay_id=:id",nativeQuery = true)
    HomestayOwnerImgHomestayReponse getImgHomestayByHomestayId(String id);

    @Query(value = "Delete from img_homestay where homestay_id=:idHomestay",nativeQuery = true)
    @Modifying
    int deleteByHomestay(String idHomestay);
}
