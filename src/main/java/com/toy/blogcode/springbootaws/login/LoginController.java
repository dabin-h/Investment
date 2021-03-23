package com.toy.blogcode.springbootaws.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @GetMapping(value = "/login")
    public ModelAndView login(ModelAndView mav) {
        mav.setViewName("login/login");
        return mav;
    }

    @GetMapping(value = "/login/getKakaoAuthUrl")
    public String getKakaoAuthUrl(ModelAndView mav) {
        String retUrl;
        retUrl = "http://localhost:8080/test";
        return retUrl;
    }

}
