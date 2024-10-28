package com.example.springhw32.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    private String writer;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
