package com.example.springhw32.service;

import com.example.springhw32.dto.PostDto;
import com.example.springhw32.entity.Post;
import com.example.springhw32.entity.User;
import com.example.springhw32.repository.PostRepository;
import com.example.springhw32.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 글 작성
    public PostDto createPost(PostDto postDto, String writerNickname) {
        User user = userRepository.findByNickname(writerNickname)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Post post = new Post();
        post.setUser(user);
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.save(post);

        return mapToDto(post);
    }

    // 최신순 글 조회
    public List<PostDto> getPostsSortedByDate() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // 작성자 글 조회
    public List<PostDto> getPostsByWriter(String writer) {
        return postRepository.findByUser_Nickname(writer).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // 댓글 많은 순 글 조회
    public List<PostDto> getPostsSortedByComments() {
        return postRepository.findAllByOrderByCommentCountDesc().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // Post -> PostDto 변환
    private PostDto mapToDto(Post post) {
        PostDto dto = new PostDto();
        dto.setPostId(post.getPostId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setWriter(post.getUser().getNickname());
        dto.setCommentCount(post.getCommentCount());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        return dto;
    }
}
