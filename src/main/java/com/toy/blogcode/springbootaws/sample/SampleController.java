package com.toy.blogcode.springbootaws.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by toy@gmail.com on 01/02/2021
 */

@RequiredArgsConstructor
@RestController
public class SampleController {

    @GetMapping("/sample/test")
    public String sample() {
        return "유민아~~! 마이 걸프랜드~~~~~! 내가 많이 애낀다!!";
    }
}