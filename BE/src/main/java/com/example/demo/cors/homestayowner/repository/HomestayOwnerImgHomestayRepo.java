package com.example.demo.cors.homestayowner.repository;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerImgHomestayReponse;
import com.example.demo.repositories.ImgHomestayRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HomestayOwnerImgHomestayRepo extends ImgHomestayRepository {

    @Query(value = "Select a.img_url from img_homestay a where a.homestay_id=:id",nativeQuery = true)
    HomestayOwnerImgHomestayReponse getImgHomestayByHomestayId(String id);

}
