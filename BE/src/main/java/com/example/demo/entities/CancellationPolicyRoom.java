package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cancellation_policy_room")
@Getter
@Setter
public class CancellationPolicyRoom extends PrimaryEntity {

<<<<<<< HEAD
    private BigDecimal price;

    @Column(length = EntityProperties.LENGTH_NAME)
    @Table(name = "cancellationpolicyroom")
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public class CancellationPolicyRoom {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer id;

        @Column(name = "name")
        private String name;
=======
    @Column(name = "name")
    private String name;
>>>>>>> 4f6e6688c2e06c1bf973a29a3d7b2ef767ff6883

        @Column(length = EntityProperties.LENGTH_NOTE, name = "[desc]")
        private String desc;

<<<<<<< HEAD
    }
=======
    @Column(length = EntityProperties.LENGTH_NAME)
    private BigDecimal price;

}
>>>>>>> 4f6e6688c2e06c1bf973a29a3d7b2ef767ff6883
