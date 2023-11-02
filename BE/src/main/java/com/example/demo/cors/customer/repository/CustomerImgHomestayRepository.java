package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerImgHomestayRequest;
import com.example.demo.cors.customer.model.response.CustomerImgHomestayResponse;
import com.example.demo.repositories.ImgHomestayRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerImgHomestayRepository extends ImgHomestayRepository {

    @Query(value = """
            SELECT a.img_url AS imgUrl
            FROM img_homestay a
            WHERE a.homestay_id =:#{#customerImgHomestayRequest.homestayId}
            """, nativeQuery = true)
    List<CustomerImgHomestayResponse> getImgHomestayByIdHomestay(CustomerImgHomestayRequest customerImgHomestayRequest);

}
