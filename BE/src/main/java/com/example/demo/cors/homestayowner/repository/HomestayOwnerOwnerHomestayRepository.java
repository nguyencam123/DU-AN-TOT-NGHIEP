package com.example.demo.cors.homestayowner.repository;

import com.example.demo.entities.OwnerHomestay;
import com.example.demo.repositories.OwnerHomestayRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
@Repository
public interface HomestayOwnerOwnerHomestayRepository extends OwnerHomestayRepository {

       @Override
       Optional<OwnerHomestay> findByUsername(String username);

       Optional<OwnerHomestay> findByResetPasswordToken(String resetPassToken);

       boolean existsByUsername(String username);

       boolean existsByEmail(String email);

       boolean existsByPhoneNumber(String phonenumber);

       boolean existsByName(String name);

}
