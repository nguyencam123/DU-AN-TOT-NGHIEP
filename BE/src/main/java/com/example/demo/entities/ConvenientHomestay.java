package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "convenient_homestay")
@Getter
@Setter
public class ConvenientHomestay extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_NAME)
    private String name;

    @ManyToOne
    @JoinColumn(name = "convenient_homestay_type_id")
    private ConvenientHomestayType convenientHomestayType;

    @Column(length = EntityProperties.LENGTH_NOTE, name = "[desc]")
    private String desc;

    @OneToMany(mappedBy = "convenientHomestay", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<DetailHomestay> detailHomestays;


}
