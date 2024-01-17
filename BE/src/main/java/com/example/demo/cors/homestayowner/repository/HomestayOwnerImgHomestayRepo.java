package com.example.demo.cors.homestayowner.repository;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerImgHomestayReponse;
import com.example.demo.entities.ImgHomestay;
import com.example.demo.repositories.ImgHomestayRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomestayOwnerImgHomestayRepo extends ImgHomestayRepository {

    @Query(value = "Select a.img_url as imgUrl from img_homestay a where a.homestay_id=:id ",nativeQuery = true)
    List<HomestayOwnerImgHomestayReponse> getImgHomestayByHomestayId(String id);

    @Query(value = "Delete from img_homestay where homestay_id=:idHomestay",nativeQuery = true)
    @Modifying
    int deleteByHomestay(String idHomestay);

    @Query(value = "select * from img_homestay where homestay_id=:id ORDER BY last_modified_date DESC",nativeQuery = true)
    List<ImgHomestay> getImgHomestayByHomestay(String id);

}
