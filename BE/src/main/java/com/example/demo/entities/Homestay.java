package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.example.demo.infrastructure.contant.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "homestay")
@Getter
@Setter
public class Homestay extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @Nationalized
    private String address;

    @Nationalized
    private String timeCheckIn;

    @Nationalized
    private String timeCheckOut;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private OwnerHomestay ownerHomestay;

    @Column(length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String name;

    private Double point;

    private Double acreage;

    private Integer roomNumber;

    private Status status;

    private Long startDate;

    private Long endDate;

    private Double CancellationPolicy;

    @Column(length = EntityProperties.LENGTH_NOTE, name = "[desc]")
    @Nationalized
    private String desc;

    private BigDecimal price;

    private Integer numberPerson;

    @OneToMany(mappedBy = "homestay", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ImgHomestay> images;

    @OneToMany(mappedBy = "homestay", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DetailHomestay> detailHomestays;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

}
