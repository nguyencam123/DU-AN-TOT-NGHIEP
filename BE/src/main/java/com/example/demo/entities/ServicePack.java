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
@Table(name = "service_pack")
@Getter
@Setter
public class ServicePack extends PrimaryEntity {

    private String code;

    @Column(length = EntityProperties.LENGTH_NAME)
    private String name;

    private BigDecimal price;

    private Integer quantityPost;

    private Integer time;

    @Column(length = EntityProperties.LENGTH_NOTE)
    private String desc;

}
