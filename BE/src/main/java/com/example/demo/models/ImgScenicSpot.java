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

@Entity
@Table(name = "imgscenicspot")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImgScenicSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",insertable = false,updatable = false)
    private Integer id;

    @Column(name = "imgurl")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "scenicspot")
    private ScenicSpot scenicSpot;
}
