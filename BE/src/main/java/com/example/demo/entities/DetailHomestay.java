package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "detail_homestay")
@Getter
@Setter
public class DetailHomestay extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "homestay_id")
    @JsonBackReference
    private Homestay homestay;

    @ManyToOne
    @JoinColumn(name = "convenient_homestay_id")
    @JsonBackReference
    private ConvenientHomestay convenientHomestay;

}
