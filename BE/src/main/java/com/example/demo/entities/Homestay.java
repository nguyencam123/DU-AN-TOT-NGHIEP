package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.example.demo.infrastructure.contant.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "homestay")
@Getter
@Setter
public class Homestay extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    private String address;

    private String timeCheckIn;

    private String timeCheckOut;

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

    @OneToMany(mappedBy = "homestay", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ImgHomestay> images;

}
