package com.example.demo.cors.login.repository;

import com.example.demo.cors.login.model.response.SignInResponse;
import com.example.demo.cors.login.model.resquest.SignInRequest;
import com.example.demo.repositories.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends UserRepository {

    @Query(value = "SELECT dbo.[user].status, dbo.[user].code, dbo.[user].name, dbo.[user].password, dbo.[user].username, dbo.role.code AS [role], dbo.role.name AS [quyen]\n" +
            "FROM     dbo.[user] INNER JOIN\n" +
            "                  dbo.role ON dbo.[user].role_id = dbo.role.id WHERE (dbo.[user].username = :#{#signInRequest.uname}) AND (dbo.[user].password = :#{#signInRequest.pass})" ,nativeQuery = true)
    SignInResponse getLogin(SignInRequest signInRequest);
}
