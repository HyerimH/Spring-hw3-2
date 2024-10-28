package com.example.springhw32.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private Long postId;

    private String title;

    private String content;

    private String writer;

    private int commentCount;
}
