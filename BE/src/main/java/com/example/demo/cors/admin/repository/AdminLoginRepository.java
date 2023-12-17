package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.request.AdminLoginRequest;
import com.example.demo.cors.admin.model.response.AdminLoginResponse;
import com.example.demo.entities.Admin;
import com.example.demo.entities.OwnerHomestay;
import com.example.demo.repositories.AdminRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
@Repository
public interface AdminLoginRepository extends AdminRepository {

    @Query(value = """
            SELECT dbo.[admin].status, dbo.[admin].code, dbo.[admin].name, dbo.[admin].password, dbo.[admin].username
            FROM dbo.[admin]  
            WHERE (dbo.[admin].username = :#{#adminLoginRequest.uname}) AND (dbo.[admin].password = :#{#adminLoginRequest.pass})
            """, nativeQuery = true)
    AdminLoginResponse getLogin(AdminLoginRequest adminLoginRequest);

    @Override
    Optional<Admin> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query(value ="\n" +
            "select b.* from token a\n" +
            "right join [admin] b on a.admin_id=b.id \n" +
            "where a.token=:token",nativeQuery = true)
    Admin findAdminByToken(String token);

}
