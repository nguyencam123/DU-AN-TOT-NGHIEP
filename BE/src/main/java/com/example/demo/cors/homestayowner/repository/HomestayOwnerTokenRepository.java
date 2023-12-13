package com.example.demo.cors.homestayowner.repository;

import com.example.demo.entities.OwnerHomestay;
import com.example.demo.entities.Token;
import com.example.demo.repositories.TokenRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface HomestayOwnerTokenRepository extends TokenRepository {

    @Query(value = """
      select t from Token t inner join  OwnerHomestay o\s
      on t.ownerHomestay.id = o.id\s
      where o.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByOwnerHomestay(String id);
}
