package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.example.demo.infrastructure.contant.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "homestay")
@Getter
@Setter
public class Homestay extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    private String address;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerHomestay ownerHomestay;

    @Column(length = EntityProperties.LENGTH_NAME)
    private String name;

    private Double point;

    private Status status;

    private Long startDate;

    private Long endDate;

    @Column(length = EntityProperties.LENGTH_NOTE, name = "[desc]")
    private String desc;

    private BigDecimal price;

    private Integer numberPerson;

}
