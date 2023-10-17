package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "scenicspot_homestay")
@Getter
@Setter
public class ScenicSpotHomestay extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "scenicspot_id")
    private ScenicSpot scenicSpot;

    @ManyToOne
    @JoinColumn(name = "homestay_id")
    private Homestay homestay;

}
