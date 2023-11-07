package com.example.demo.cors.homestayowner.repository;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayReponse;
import com.example.demo.entities.Homestay;
import com.example.demo.repositories.HomestayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HomestayOwnerHomestayRepository extends HomestayRepository {

    @Query(value = "\t   SELECT a.id,\n" +
            "       a.name,\n" +
            "       a.address,\n" +
            "       a.price,\n" +
            "       a.start_date AS startDate,\n" +
            "       a.number_person AS numberPerson,\n" +
            "       a.status,\n" +
            "       imageUrls.imgUrls AS imageUrls\n" +
            "FROM homestay a\n" +
            "inner join detail_homestay h on a.id=h.homestay_id\n" +
            "inner join convenient_homestay e on h.convenient_homestay_id=e.id\n" +
            "CROSS APPLY (\n" +
            "    SELECT STRING_AGG(img_url, ', ') AS imgUrls\n" +
            "    FROM img_homestay AS d\n" +
            "    WHERE d.homestay_id = a.id\n" +
            ") AS imageUrls where e.id=:id", nativeQuery = true)
    Page<HomestayOwnerHomestayReponse> getHomestayByConvient(String id, Pageable pageable);

    @Query(value = "Select * from homestay a\n" +
            " where a.owner_id='163cc112-3f66-40c2-be38-9d9cd536eb90' and (a.status=1 or a.status=0)", nativeQuery = true)
    Page<Homestay> getHomestayByOwnerH(String id, Pageable pageable);

}
