package com.toy.blogcode.springbootaws.post.controller;

import com.toy.blogcode.springbootaws.common.CommonResponse;
import com.toy.blogcode.springbootaws.common.response.CommonListResult;
import com.toy.blogcode.springbootaws.common.response.CommonResult;
import com.toy.blogcode.springbootaws.model.repository.PostRepository;
import com.toy.blogcode.springbootaws.model.entity.Posts;
import com.toy.blogcode.springbootaws.model.dto.PostsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final PostRepository postsRepository;
    private final CommonResponse commonResponse;

    public PostController(PostRepository postsRepository, CommonResponse commonResponse){
        this.commonResponse = commonResponse;
        this.postsRepository = postsRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/posts")
    public CommonResult savePosts(@RequestBody PostsDto dto){
        postsRepository.save(dto.toEntity());
        return commonResponse.normalResult();
    }

    @GetMapping("/find")
    public CommonListResult<Posts> selectPosts(){
        List<Posts> postsList = postsRepository.findAll();
        return commonResponse.listResult(postsList);
    }
}
