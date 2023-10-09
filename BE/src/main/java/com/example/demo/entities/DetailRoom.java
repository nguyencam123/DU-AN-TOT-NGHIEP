package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "detailroom")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DetailRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "cancellationpolicyroom")
    private CancellationPolicyRoom cancellationPolicyRoom;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "createddate")
    private Date createdDate;

    @Column(name = "updateddate")
    private Date updatedDate;
}
