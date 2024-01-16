package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.request.AdminHomestayRequest;
import com.example.demo.cors.admin.model.request.AdminOwnerHomestayRequest;
import com.example.demo.entities.OwnerHomestay;
import com.example.demo.entities.User;
import com.example.demo.repositories.OwnerHomestayRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminOwnerHomestayRepository extends OwnerHomestayRepository {
    @Query(value = """
            SELECT ROW_NUMBER() OVER(ORDER BY oh.last_modified_date DESC) AS stt, oh.* FROM owner_homestay oh\s
            WHERE ( ( :#{#request.statusOwnerHomestay} IS NULL OR oh.status = :#{#request.statusOwnerHomestay} )
            AND ( :#{#request.nameOwner} IS NULL OR oh.name LIKE '' OR oh.name LIKE %:#{#request.nameOwner}% ) )
            """, nativeQuery = true)
    Page<OwnerHomestay> getAllOwner(Pageable pageable,  AdminOwnerHomestayRequest request);

    boolean existsByUsername(String username);
}
