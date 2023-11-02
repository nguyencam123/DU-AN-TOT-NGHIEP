package com.example.demo.cors.homestayowner.repository;

import com.example.demo.cors.homestayowner.model.reponse.HomestayOwnerLoginReponse;
import com.example.demo.cors.homestayowner.model.request.HomestayownerLoginRequest;
import com.example.demo.entities.OwnerHomestay;
import com.example.demo.repositories.OwnerHomestayRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
@Repository
public interface HomestayOwnerOwnerHomestayRepository extends OwnerHomestayRepository {

       @Query(value = "Select a.code,a.name,a.email,a.password,a.username,a.status \n" +
               "from owner_homestay a \n" +
               "where a.username= :#{#homestayownerLoginRequest.uname} and a.password=:#{#homestayownerLoginRequest.pass}",nativeQuery = true)
       HomestayOwnerLoginReponse getLoginOwnerHomestay(HomestayownerLoginRequest homestayownerLoginRequest);

       @Override
       Optional<OwnerHomestay> findByUsername(String username);

       boolean existsByUsername(String username);

       boolean existsByEmail(String email);

}
