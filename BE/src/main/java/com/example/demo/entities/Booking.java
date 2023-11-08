package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.example.demo.infrastructure.contant.StatusBooking;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Entity
@Table(name = "booking")
@Getter
@Setter
public class Booking extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private StatusBooking status;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    private Long startDate;

    private Long endDate;

    @Nationalized
    private String name;

    @Column(length = EntityProperties.LENGTH_EMAIL)
    @Nationalized
    private String email;

    @Column(length = EntityProperties.LENGTH_PHONE)
    @Nationalized
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "homestay_id")
    private Homestay homestay;

}
