package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "convenient_homestay_type")
@Getter
@Setter
public class ConvenientHomestayType extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String name;

    @Column(length = EntityProperties.LENGTH_NOTE, name = "[desc]")
    @Nationalized
    private String desc;

}
