package com.example.demo.entities;


import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.example.demo.infrastructure.contant.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_rank")
@Getter
@Setter
public class CustomerRank extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_NAME)
    private String name;

    @Column(length = EntityProperties.LENGTH_NOTE, name = "[desc]")
    private String desc;

    private Integer minimunPoint;

    private Integer kindSale;

    private Double value;

    private Status status;

}
