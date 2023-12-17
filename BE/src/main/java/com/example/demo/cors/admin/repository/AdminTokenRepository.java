package com.example.demo.cors.admin.repository;

import com.example.demo.entities.Token;
import com.example.demo.repositories.TokenRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminTokenRepository extends TokenRepository {
    @Query(value = """
      select t from Token t inner join  Admin a\s
      on t.admin.id = a.id\s
      where a.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(String id);
}
