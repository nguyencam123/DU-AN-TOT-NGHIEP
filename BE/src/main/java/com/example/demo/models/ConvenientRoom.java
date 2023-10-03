package com.example.demo.models;


import com.example.demo.models.Embeddable.ConvenientRoomId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
