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

@Entity
@Table(name = "imghotel")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ImgHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false,insertable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "hotel")
    private Hotel hotel;

    @Column(name = "id")
    private String imgUrl;
}
