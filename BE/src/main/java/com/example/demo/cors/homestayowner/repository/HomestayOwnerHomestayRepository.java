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

      @Query(value = "select a.name as name,b.name as address,c.name as servicePack,a.start_date as statusDate,a.status as statusHomestay,a.status_service_pack as statusServicePack  from homestay a \n" +
              "inner join address b on a.address_id=b.id \n" +
              "inner join service_pack c on a.service_pack_id=c.id",nativeQuery = true)
      Page<HomestayOwnerHomestayReponse> getALlHomestayPage(Pageable pageable);

      @Query(value = "select a.name as name,b.name as address,e.name as servicePack,a.start_date as statusDate,a.status as statusHomestay,a.status_service_pack as statusServicePack \n" +
              "from homestay a  \n" +
              "inner join [address] b on a.address_id=b.id\n" +
              "inner join service_pack e on a.service_pack_id=e.id\n" +
              "inner join convenient_homestay d on a.id=d.homestay_id\n" +
              "where d.id=:id",nativeQuery = true)
      Page<HomestayOwnerHomestayReponse> getHomestayByConvient(String id,Pageable pageable);

}
