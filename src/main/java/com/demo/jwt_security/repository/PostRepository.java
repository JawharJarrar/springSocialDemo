package com.demo.jwt_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.jwt_security.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {


}
