package com.example.demo.entities.Embeddable;

import com.example.demo.entities.Booking;
import com.example.demo.entities.Room;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class DetailBookingId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "booking", insertable = false,updatable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;
}
