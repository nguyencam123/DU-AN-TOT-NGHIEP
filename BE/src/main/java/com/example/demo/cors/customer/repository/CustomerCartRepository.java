package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerCartRequest;
import com.example.demo.entities.Cart;
import com.example.demo.entities.Homestay;
import com.example.demo.repositories.CartRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCartRepository extends CartRepository {

    @Query(value = """
            SELECT c.* 
            FROM cart c
            WHERE c.user_id = :#{#request.userId} 
            """, nativeQuery = true)
    Page<Cart> getAllHomestayInCart(Pageable pageable, @Param("request") CustomerCartRequest request);

}
