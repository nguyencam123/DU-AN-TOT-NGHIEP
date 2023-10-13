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
@Table(name = "detail_room")
@Getter
@Setter
public class DetailRoom extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "cancellation_policy_room_id")
    private CancellationPolicyRoom cancellationPolicyRoom;

    private BigDecimal price;

    private Integer paymentType;

}
