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
@Table(name = "convenient_room_type")
@Getter
@Setter
public class ConvenientRoomType extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_NAME)
    private String name;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(length = EntityProperties.LENGTH_NOTE, name = "[desc]")
    private String desc;

}
