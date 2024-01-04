package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerCartRequest;
import com.example.demo.entities.Cart;
import com.example.demo.repositories.CartRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCartRepository extends CartRepository {

    @Query(value = """
            SELECT * FROM cart
            WHERE id_user = :#{#request.userId} 
            """, nativeQuery = true)
    Cart findByUserId(@Param("request") CustomerCartRequest request);

}
