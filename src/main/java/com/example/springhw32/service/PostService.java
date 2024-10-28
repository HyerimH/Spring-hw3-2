package com.example.springhw32.service;

import com.example.springhw32.dto.PostDto;
import com.example.springhw32.entity.Post;
import com.example.springhw32.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 글 작성
    @Transactional
    public PostDto createPost(PostDto postDto, Long userId) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setWriter(postDto.getWriter());
        post.setCommentCount(0);
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
        postDto.setPostId(post.getId());
        return postDto;
    }

    // 최신순 글 조회
    public List<PostDto> getPostsSortedByDate() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // 작성자 글 조회
    public List<PostDto> getPostsByWriter(String writer) {
        return postRepository.findByWriter(writer).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // 댓글 많은 순 조회
    public List<PostDto> getPostsSortedByComments() {
        return postRepository.findAllByOrderByCommentCountDesc().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Dto로 매핑
    private PostDto mapToDto(Post post) {
        PostDto dto = new PostDto();
        dto.setPostId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setWriter(post.getWriter());
        dto.setCommentCount(post.getCommentCount());
        return dto;
    }
}
