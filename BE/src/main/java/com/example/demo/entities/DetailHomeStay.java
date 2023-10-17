package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "detail_homestay")
@Getter
@Setter
public class DetailHomeStay extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "homestay_id")
    private Homestay homestay;

    @ManyToOne
    @JoinColumn(name = "cancellation_policy_room_id")
    private CancellationPolicyRoom cancellationPolicyRoom;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

}
