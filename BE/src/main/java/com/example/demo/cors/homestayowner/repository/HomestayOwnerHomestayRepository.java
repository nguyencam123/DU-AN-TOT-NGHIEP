package com.example.demo.cors.homestayowner.repository;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayReponse;
import com.example.demo.entities.Homestay;
import com.example.demo.repositories.HomestayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomestayOwnerHomestayRepository extends HomestayRepository {

    @Query(value = "Select * from homestay a\n" +
            " where a.owner_id=:id and (a.status=1 or a.status=0)", nativeQuery = true)
    Page<Homestay> getHomestayByOwnerH(String id, Pageable pageable);

    @Query(value="select a.* from homestay a \n" +
            "left join detail_homestay b on a.id=b.homestay_id \n" +
            "left join convenient_homestay c on b.convenient_homestay_id=c.id\n" +
            "where c.id=:id and a.status=1; ",nativeQuery = true)
    Page<HomestayOwnerHomestayReponse> getHomestayByConvient(String id, Pageable pageable);
}
