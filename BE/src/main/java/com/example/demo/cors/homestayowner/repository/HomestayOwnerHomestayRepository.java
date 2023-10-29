package com.example.demo.cors.homestayowner.repository;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayReponse;
import com.example.demo.repositories.HomestayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface HomestayOwnerHomestayRepository extends HomestayRepository {

      @Query(value ="SELECT a.id,\n" +
              "       a.name,\n" +
              "       a.address,\n" +
              "       a.price,\n" +
              "       a.start_date AS startDate,\n" +
              "       a.number_person AS numberPerson,\n" +
              "       b.name AS province,\n" +
              "       c.name AS region,\n" +
              "       a.status,\n" +
              "       imageUrls.imgUrls AS imageUrls\n" +
              "FROM homestay a\n" +
              "INNER JOIN province b ON a.province_id = b.id\n" +
              "INNER JOIN region c ON a.region_id = c.id\n" +
              "CROSS APPLY (\n" +
              "    SELECT STRING_AGG(img_url, ', ') AS imgUrls\n" +
              "    FROM img_homestay AS d\n" +
              "    WHERE d.homestay_id = a.id\n" +
              ") AS imageUrls;", nativeQuery = true)
      Page<HomestayOwnerHomestayReponse> getALlHomestayPage(Pageable pageable);

      @Query(value = "\t   SELECT a.id,\n" +
              "       a.name,\n" +
              "       a.address,\n" +
              "       a.price,\n" +
              "       a.start_date AS startDate,\n" +
              "       a.number_person AS numberPerson,\n" +
              "       b.name AS province,\n" +
              "       c.name AS region,\n" +
              "       a.status,\n" +
              "       imageUrls.imgUrls AS imageUrls\n" +
              "FROM homestay a\n" +
              "INNER JOIN province b ON a.province_id = b.id\n" +
              "INNER JOIN region c ON a.region_id = c.id\n" +
              "inner join detail_homestay h on a.id=h.homestay_id\n" +
              "inner join convenient_homestay e on h.convenient_homestay_id=e.id\n" +
              "CROSS APPLY (\n" +
              "    SELECT STRING_AGG(img_url, ', ') AS imgUrls\n" +
              "    FROM img_homestay AS d\n" +
              "    WHERE d.homestay_id = a.id\n" +
              ") AS imageUrls where e.id=:id",nativeQuery = true)
      Page<HomestayOwnerHomestayReponse> getHomestayByConvient(String id,Pageable pageable);

}
