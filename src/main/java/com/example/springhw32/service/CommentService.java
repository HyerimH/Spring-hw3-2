package com.example.springhw32.service;

import com.example.springhw32.dto.CommentDto;
import com.example.springhw32.entity.Comment;
import com.example.springhw32.entity.Post;
import com.example.springhw32.repository.CommentRepository;
import com.example.springhw32.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 댓글 작성
    @Transactional
    public CommentDto createComment(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setWriter(commentDto.getWriter());
        comment.setPost(post);
        commentRepository.save(comment);
        commentDto.setPostId(post.getId());
        return commentDto;
    }

    // 특정 게시물에 달린 모든 댓글 조회
    public List<CommentDto> getAllCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(comment -> mapToDto(comment, postId))
                .collect(Collectors.toList());
    }

    // Dto로 매핑
    private CommentDto mapToDto(Comment comment, Long postId) {
        CommentDto dto = new CommentDto();
        dto.setPostId(postId);
        dto.setContent(comment.getContent());
        dto.setWriter(comment.getWriter());
        return dto;
    }
}
