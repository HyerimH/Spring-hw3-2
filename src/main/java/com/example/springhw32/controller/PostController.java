package com.example.springhw32.controller;

import com.example.springhw32.dto.PostDto;
import com.example.springhw32.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 글 작성
    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto, @RequestParam String writerNickname) {
        return postService.createPost(postDto, writerNickname);
    }
    // 최신순 글 조회
    @GetMapping
    public List<PostDto> getPostsSortedByDate() {
        return postService.getPostsSortedByDate();
    }

    // 작성자 글 조회
    @GetMapping("/{writer}")
    public List<PostDto> getPostsByWriter(@PathVariable String writer) {
        return postService.getPostsByWriter(writer);
    }

    // 댓글 많은 순 글 조회
    @GetMapping("/comments")
    public List<PostDto> getPostsSortedByComments() {
        return postService.getPostsSortedByComments();
    }
}
