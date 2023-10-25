package com.example.demo.cors.homestayowner.repository;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerLoginReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayownerLoginRequest;
import com.example.demo.repositories.OwnerHomestayRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HomestayOwnerOwnerHomestayRepository extends OwnerHomestayRepository {
       @Query(value = "Select a.code,a.name,a.email,a.password,a.username,a.status \n" +
               "from owner_homestay a \n" +
               "where a.username= :#{#homestayownerLoginRequest.uname} and a.password=:#{#homestayownerLoginRequest.pass}",nativeQuery = true)
       HomestayOwnerLoginReponse getLoginOwnerHomestay(HomestayownerLoginRequest homestayownerLoginRequest);

}
