package com.demo.jwt_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.jwt_security.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {


}
