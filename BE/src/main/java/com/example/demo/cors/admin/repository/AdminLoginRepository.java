package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.response.AdminLoginResponse;
import com.example.demo.cors.admin.model.request.AdminLoginRequest;
import com.example.demo.repositories.AdminRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLoginRepository extends AdminRepository {

    @Query(value = """
            SELECT dbo.[admin].status, dbo.[admin].code, dbo.[admin].name, dbo.[admin].password, dbo.[admin].username
            FROM dbo.[admin]  
            WHERE (dbo.[admin].username = :#{#adminLoginRequest.uname}) AND (dbo.[admin].password = :#{#adminLoginRequest.pass})
            """, nativeQuery = true)
    AdminLoginResponse getLogin(AdminLoginRequest adminLoginRequest);

}
