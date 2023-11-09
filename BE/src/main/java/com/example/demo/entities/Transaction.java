package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.example.demo.infrastructure.contant.StatusPayment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "[transaction]")
@Getter
@Setter
public class Transaction extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "id_booking")
    private Booking booking;

    @Enumerated(EnumType.STRING)
    private StatusPayment statusPayment;

    private BigDecimal totalMoney;

    @Column(length = EntityProperties.LENGTH_NOTE, name = "[desc]")
    private String description;

}
