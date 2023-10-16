package com.example.demo.entities;

import com.example.demo.entities.base.PrimaryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "img_comment")
@Getter
@Setter
public class ImgComment extends PrimaryEntity {

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private String imgUrl;

}
