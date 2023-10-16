package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "history_service_pack")
@Getter
@Setter
public class HistoryServicePack extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "homestay_id")
    private Homestay homestay;

    @ManyToOne
    @JoinColumn(name = "service_pack_id")
    private ServicePack servicePack;

    private Long startDate;

    private Long endDate;

}
