package com.example.springhw32.service;

import com.example.springhw32.dto.CommentDto;
import com.example.springhw32.entity.Comment;
import com.example.springhw32.entity.Post;
import com.example.springhw32.entity.User;
import com.example.springhw32.repository.CommentRepository;
import com.example.springhw32.repository.PostRepository;
import com.example.springhw32.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post not found"));

        User user = userRepository.findById(commentDto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        post.setCommentCount(post.getCommentCount() + 1L);
        postRepository.save(post);
        return mapToDto(comment);
    }


    // 특정 게시물에 달린 모든 댓글 조회
    public List<CommentDto> getAllCommentsByPostId(Long postId) {
        return commentRepository.findByPost_PostId(postId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Comment -> CommentDto 변환
    private CommentDto mapToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setCommentId(comment.getCommentId());
        dto.setPostId(comment.getPost().getPostId());
        dto.setUserId(comment.getUser().getId());
        dto.setContent(comment.getContent());
        return dto;
    }
}
