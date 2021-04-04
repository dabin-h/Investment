package com.toy.blogcode.springbootaws.post.controller;

import com.toy.blogcode.springbootaws.common.CommonResponse;
import com.toy.blogcode.springbootaws.common.response.CommonListResult;
import com.toy.blogcode.springbootaws.common.response.CommonResult;
import com.toy.blogcode.springbootaws.common.response.CommonSingleResult;
import com.toy.blogcode.springbootaws.model.entity.User;
import com.toy.blogcode.springbootaws.model.repository.PostRepository;
import com.toy.blogcode.springbootaws.model.entity.Posts;
import com.toy.blogcode.springbootaws.model.dto.PostsDto;
import com.toy.blogcode.springbootaws.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostRepository postsRepository;
    private final CommonResponse commonResponse;
    private final JwtTokenProvider jwtTokenProvider;

    public PostController(PostRepository postsRepository, CommonResponse commonResponse, JwtTokenProvider jwtTokenProvider){
        this.commonResponse = commonResponse;
        this.postsRepository = postsRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping(value = "/authTest")
    public CommonSingleResult<String> authTest(@RequestBody User user) {
        //TODO User의 아이디와 패스워드로 User정보를 찾는다.
        //TODO 받은 jwtData는 Post요청시 활용한다.
        return commonResponse.singleResult(jwtTokenProvider.createToken(String.valueOf(user.getUserId()), user.getRoles()));
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
