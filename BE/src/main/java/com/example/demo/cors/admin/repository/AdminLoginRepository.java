package com.example.demo.cors.admin.repository;

import com.example.demo.cors.admin.model.reponse.AdminLoginReponsi;
import com.example.demo.cors.admin.model.request.AdminLoginRequest;
import com.example.demo.repositories.AdminRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminLoginRepository extends AdminRepository {
    @Query(value = "SELECT dbo.[admin].status, dbo.[admin].code, dbo.[admin].name, dbo.[admin].password, dbo.[admin].username\n" +
            "FROM     dbo.[admin]  WHERE (dbo.[admin].username = :#{#adminLoginRequest.username}) AND (dbo.[admin].password = :#{#adminLoginRequest.pass})" ,nativeQuery = true)
    AdminLoginReponsi getLogin(AdminLoginRequest adminLoginRequest);
}
