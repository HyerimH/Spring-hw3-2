package com.example.springhw32.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {
    private Long postId;

    private String title;

    private String content;

    private String writer;

    private Long commentCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
