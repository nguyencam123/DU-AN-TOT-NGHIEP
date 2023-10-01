package com.example.demo.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customerrank")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String desc;

    @Column(name = "minimunpoint")
    private Integer minimunPoint;

    @Column(name = "kindsale")
    private Integer kindSale;

    @Column(name = "value")
    private Double value;

    @Column(name = "status")
    private Integer status;

}
