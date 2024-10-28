package com.example.springhw32.repository;

import com.example.springhw32.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시물에 달린 모든 댓글 조회
    List<Comment> findByPostId(Long postId);
}

