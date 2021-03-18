package com.toy.blogcode.springbootaws.post.controller;

import com.toy.blogcode.springbootaws.post.domain.PostRepository;
import com.toy.blogcode.springbootaws.post.domain.Posts;
import com.toy.blogcode.springbootaws.post.dto.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Table;
import java.util.List;

@RestController
@AllArgsConstructor
public class PostController {

    PostRepository postsRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/posts")
    public void savePosts(@RequestBody PostsSaveRequestDto dto){
        postsRepository.save(dto.toEntity());
    }

    @GetMapping("/find")
    public List<Posts> selectPosts(){
        List<Posts> postsList = postsRepository.findAll();
        return postsList;
    }
}
