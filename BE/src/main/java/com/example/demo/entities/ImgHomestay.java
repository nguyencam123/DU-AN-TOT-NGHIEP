package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "img_homestay")
@Getter
@Setter
public class ImgHomestay extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "homestay_id")
    private Homestay homestay;

    private String imgUrl;

}
