package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hotel")
@Getter
@Setter
public class Hotel extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_NAME)
    private String name;

    private String address;

    private String websiteUrl;

    private Double star;

    @Column(length = EntityProperties.LENGTH_NOTE, name = "[desc]")
    private String desc;

}
