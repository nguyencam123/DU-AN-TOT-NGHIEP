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

@Entity
@Table(name = "img_hotel")
@Getter
@Setter
public class ImgHotel extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private String imgUrl;

}
