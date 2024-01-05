package com.example.demo.cors.customer.repository;

import com.example.demo.cors.customer.model.request.CustomerCartRequest;
import com.example.demo.entities.CartDetail;
import com.example.demo.repositories.CartDetailRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerCartDetailRepository extends CartDetailRepository {

    @Query(value = """
            SELECT * FROM cart_detail
            WHERE id_cart = :#{#idCart}
            """, nativeQuery = true)
    List<CartDetail> listCartDetail(String idCart);

    @Query(value = """
           SELECT * FROM cart_detail
            """, nativeQuery = true)
    List<CartDetail> getAllCartDetail();

    @Modifying
    @Transactional
    @Query(value = """
            DELETE cd FROM cart_detail cd
            JOIN cart c ON cd.id_cart = c.id
            JOIN [user] u ON c.id_user = u.id
            WHERE u.id = :#{#userId} 
            """,nativeQuery = true)
    void deleteAllCart(String userId);

}
