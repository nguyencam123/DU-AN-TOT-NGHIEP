package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.example.demo.infrastructure.contant.TypePromotion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "promotion")
@Getter
@Setter
public class Promotion extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_NAME)
    private String name;

    private Long startDate;

    private Long endDate;

    private TypePromotion type;

    private Double value;

}
