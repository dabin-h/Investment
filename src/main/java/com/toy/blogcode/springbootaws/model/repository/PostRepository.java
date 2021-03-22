package com.toy.blogcode.springbootaws.model.repository;

import com.toy.blogcode.springbootaws.model.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> {
}
