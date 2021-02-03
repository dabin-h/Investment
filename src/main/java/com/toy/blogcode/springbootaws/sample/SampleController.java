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
        return "sample TEST";
    }
}