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

      @Query(value = "select  a.name , a.address, a.price,a.start_date as startDate,a.number_person as numberPerson,b.name as province,c.name as region,a.status from homestay a\n" +
              "inner join province b on a.province_id=b.id\n" +
              "inner join region c on a.region_id=c.id",nativeQuery = true)
      Page<HomestayOwnerHomestayReponse> getALlHomestayPage(Pageable pageable);

      @Query(value = "select  a.name , a.address, a.price,a.start_date as startDate,a.number_person as numberPerson,b.name as province,c.name as region,a.status from homestay a\n" +
              "inner join province b on a.province_id=b.id\n" +
              "inner join region c on a.region_id=c.id\n" +
              "inner join detail_homestay d on a.id=d.homestay_id\n" +
              "inner join convenient_homestay e on d.convenient_homestay_id=e.id\n" +
              "where e.id=:id",nativeQuery = true)
      Page<HomestayOwnerHomestayReponse> getHomestayByConvient(String id,Pageable pageable);

}
