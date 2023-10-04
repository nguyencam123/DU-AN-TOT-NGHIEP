package com.example.demo.entities.Embeddable;

import com.example.demo.entities.Convenient;
import com.example.demo.entities.Hotel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ConvenientHotelId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "hotel")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "convenient")
    private Convenient convenient;
}
