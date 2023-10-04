package com.example.demo.entities;


import com.example.demo.entities.Embeddable.ConvenientRoomId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "convenientroom")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ConvenientRoom {

    @EmbeddedId
    private ConvenientRoomId id;

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String desc;

}
