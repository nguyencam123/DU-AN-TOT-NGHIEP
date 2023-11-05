package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import com.example.demo.infrastructure.contant.EntityProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Homestay homestay;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double point;

    @Column(length = EntityProperties.LENGTH_NOTE)
    private String comment;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ImgComment> images;

}
