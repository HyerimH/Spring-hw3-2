package com.example.springhw32.controller;

import com.example.springhw32.dto.CommentDto;
import com.example.springhw32.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping
    public CommentDto createComment(@RequestBody CommentDto commentDto) {
        return commentService.createComment(commentDto); // 댓글 생성
    }

    // 특정 게시물에 달린 모든 댓글 조회
    @GetMapping("/{postId}")
    public List<CommentDto> getAllCommentsByPostId(@PathVariable Long postId) {
        return commentService.getAllCommentsByPostId(postId); // 댓글 목록 조회
    }
}
