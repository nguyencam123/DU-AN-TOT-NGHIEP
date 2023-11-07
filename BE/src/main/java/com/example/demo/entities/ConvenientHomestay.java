package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "convenient_homestay")
@Getter
@Setter
public class ConvenientHomestay extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String name;

    @ManyToOne
    @JoinColumn(name = "convenient_homestay_type_id")
    private ConvenientHomestayType convenientHomestayType;

    @Column(length = EntityProperties.LENGTH_NOTE, name = "[desc]")
    @Nationalized
    private String desc;

}
