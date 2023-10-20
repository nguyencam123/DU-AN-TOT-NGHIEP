package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "detail_booking")
@Getter
@Setter
public class DetailBooking extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "booking_id", insertable = false, updatable = false)
    private Booking booking;

    private Long dateStart;

    private Long dateEnd;

    @Column(length = EntityProperties.LENGTH_NOTE, name = "[desc]")
    private String desc;

}
