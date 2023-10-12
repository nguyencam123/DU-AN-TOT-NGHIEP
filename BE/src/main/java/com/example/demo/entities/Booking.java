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
@Table(name = "booking")
@Getter
@Setter
public class Booking extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long dateBooking;

    private Status status;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "customer_rank_id")
    private CustomerRank customerRank;

}
