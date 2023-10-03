package com.example.demo.models.Embeddable;

import com.example.demo.models.Convenient;
import com.example.demo.models.Room;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ConvenientRoomId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "convenient")
    private Convenient convenient;
}