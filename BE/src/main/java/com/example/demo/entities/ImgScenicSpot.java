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
@Table(name = "img_scenic_spot")
@Getter
@Setter
public class ImgScenicSpot extends PrimaryEntity {

    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "scenicspot_id")
    private ScenicSpot scenicSpot;
}

