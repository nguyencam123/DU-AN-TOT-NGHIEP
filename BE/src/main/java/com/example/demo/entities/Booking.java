package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.example.demo.infrastructure.contant.PaymentStatusBooking;
import com.example.demo.infrastructure.contant.StatusBooking;
import com.example.demo.infrastructure.contant.TypeBooking;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "homestay_id")
    private Homestay homestay;

    private TypeBooking typeBooking;

    @Nationalized
    private String note;

    private String customerTransactionCode;

    private String adminTransactionCode;

    private PaymentStatusBooking paymentStatusBooking;

    private BigDecimal pricePerNight;

    private BigDecimal promotionPrice;

    private Integer numberOfNight;

    private LocalDate cancellationDate;

}
