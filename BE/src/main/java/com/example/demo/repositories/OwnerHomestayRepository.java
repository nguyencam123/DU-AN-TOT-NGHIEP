package com.example.demo.repositories;

import com.example.demo.entities.OwnerHomestay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(OwnerHomestayRepository.NAME)
public interface OwnerHomestayRepository extends JpaRepository<OwnerHomestay, String> {
    public static final String NAME = "BaseOwnerHomestayRepository";

    Optional<OwnerHomestay> findByUsername(String username);

}
