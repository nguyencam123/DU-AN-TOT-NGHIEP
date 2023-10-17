package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerLoginRequest;
import com.example.demo.cors.customer.model.response.CustomerLoginResponse;
import com.example.demo.repositories.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerLoginRepository extends UserRepository {

    @Query(value = """
            SELECT a.status, a.code, a.name, a.password, a.username, a.phone_number, a.email
            FROM dbo.[user] a
            WHERE (a.username =:#{#customerLoginRequest.uname}) AND (a.password =:#{#customerLoginRequest.pass})
            """, nativeQuery = true)
    CustomerLoginResponse getCustomerLogin(CustomerLoginRequest customerLoginRequest);
}
