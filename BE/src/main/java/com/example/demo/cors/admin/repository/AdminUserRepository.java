package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.request.AdminUserRequest;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends UserRepository {
    @Query(value = """
            SELECT ROW_NUMBER() OVER(ORDER BY u.created_date DESC) AS stt, u.* FROM [user] u \s
            WHERE ( :#{#request.userName} IS NULL OR u.name LIKE '' OR u.name LIKE %:#{#request.userName}% )
            """, nativeQuery = true)
    Page<User> getAllUser(Pageable pageable, AdminUserRequest request);
}
