package com.example.springhw32.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long postId;

    private String writer;

    private String content;
}
