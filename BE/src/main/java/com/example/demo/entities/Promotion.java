package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.example.demo.infrastructure.contant.TypePromotion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "promotion")
@Getter
@Setter
public class Promotion extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String name;

    private Long startDate;

    private Long endDate;

    private TypePromotion type;

    private Double value;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerHomestay idOwnerHomestay;
}
