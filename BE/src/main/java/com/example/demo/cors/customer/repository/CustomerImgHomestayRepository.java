package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerImgHomestayRequest;
import com.example.demo.cors.customer.model.response.CustomerImgHomestayResponse;
import com.example.demo.entities.ImgHomestay;
import com.example.demo.repositories.ImgHomestayRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerImgHomestayRepository extends ImgHomestayRepository {

    @Query(value = """
            SELECT * FROM img_homestay a
            WHERE a.homestay_id =:#{#customerImgHomestayRequest.homestayId}
            ORDER BY a.last_modified_date DESC
            """, nativeQuery = true)
    List<ImgHomestay> getImgHomestayByIdHomestay(CustomerImgHomestayRequest customerImgHomestayRequest);

}
