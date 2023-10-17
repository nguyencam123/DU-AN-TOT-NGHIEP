package com.example.demo.cors.homestayowner.repository;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerHomestayReponse;
import com.example.demo.repositories.HomestayRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HomestayOwnerHomestayRepository extends HomestayRepository {

      @Query(value = "select a.name as name,b.name as address,c.name as servicePack,a.start_date as statusDate,a.status as statusHomestay,a.status_service_pack as statusServicePack  from homestay a \n" +
              "inner join address b on a.address_id=b.id \n" +
              "inner join service_pack c on a.service_pack_id=c.id",nativeQuery = true)
      Page<HomestayOwnerHomestayReponse> getALlHomestayPage(Pageable pageable);

}
