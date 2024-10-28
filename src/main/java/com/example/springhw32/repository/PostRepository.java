package com.example.springhw32.repository;

import com.example.springhw32.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByWriter(String writer);
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findAllByOrderByCommentCountDesc();
}

