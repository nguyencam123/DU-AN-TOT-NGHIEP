package com.example.demo.models;


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
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "hotel")
    private Hotel hotel;

    @Column(name = "code")
    private String ma;

    @Column(name = "name")
    private String name;

    @Column(name = "idmoney")
    private BigDecimal money;

    @Column(name = "status")
    private Integer status;

    @Column(name = "squre")
    private Double squre;

    @Column(name = "maxadult")
    private Integer maxAdult;

    @Column(name = "maxchildren")
    private Integer maxChildren;

    @Column(name = "kingbed")
    private Integer kingBed;

    @Column(name = "queenbed")
    private Integer queenBed;

    @Column(name = "twinbed")
    private Integer twinBed;

    @Column(name = "singlebed")
    private Integer singleBed;

    @Column(name = "bath")
    private Integer bath;

    @Column(name = "createddate")
    private Date createdDate;

    @Column(name = "updateddate")
    private Date updatedDate;

}
