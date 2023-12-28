package com.example.demo.cors.customer.repository;

import com.example.demo.entities.OwnerHomestay;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
@Repository

public interface CustomerLoginRepository extends UserRepository {

    @Override
    Optional<User> findByUsername(String username);

    Optional<User> findById(String id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phonenumber);

    @Query(value ="select b.* from token a\n" +
            "right join [user] b on a.user_id=b.id \n" +
            "where a.token=:token",nativeQuery = true)
    User findUserByToken(String token);
}
