package com.toy.blogcode.springbootaws.sample;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SampleController {

    @GetMapping("/sample")
    public String sample() {
        return "sample test";
    }

    @GetMapping(value = "/sample/test")
    public ModelAndView test(ModelAndView mav) {
        mav.setViewName("sample/test");
        return mav;
    }

}