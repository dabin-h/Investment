package com.toy.blogcode.springbootaws.login.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.toy.blogcode.springbootaws.login.service.KakaoAPI;

@RestController
public class LoginController {

    @Autowired
    KakaoAPI kakao;

    @GetMapping(value = "/login")
    public ModelAndView login(ModelAndView mav) {
        mav.setViewName("login/login");
        return mav;
    }

    // 카카오 로그인창 호출
    @RequestMapping(value = "/login/getKakaoAuthUrl")
    public @ResponseBody String getKakaoAuthUrl(HttpServletRequest request) throws Exception {
        String reqUrl = kakao.getKakaoAuthUrl(request);
        return reqUrl;
    }

    @RequestMapping(value = "/login/oauth_kakao")
    private String oauthKakao(@RequestParam(value = "code", required = false) String code, HttpServletRequest request) throws Exception{
        String userID;

        HttpSession session = request.getSession();

        JsonNode accessToken = kakao.getAccessToken(code);

        if (session.getAttribute("access_token") == null) {
            session.setAttribute("access_token", (String)accessToken.get("access_token").asText());
        }

        if ((String)session.getAttribute("access_token") != null &&
                (String)session.getAttribute("access_token") != "") {
            userID = kakao.getUserInfo((String)session.getAttribute("access_token"));
            session.setAttribute("userid", userID);

            if (userID != "" && userID != null) {
                return userID + "님, 환영합니다!";
            }
            else {
                return "유저 정보 없음";
            }
        }
        else {
            return "토큰 에러";
        }
    }

    //카카오 로그아웃
    @RequestMapping(value = "/login/logout")
    public String logout(HttpSession session) {
        kakao.kakaoLogout((String)session.getAttribute("access_token"));
        session.removeAttribute("access_token");
        session.removeAttribute("userid");

        System.out.println("세션없음:" + (String)session.getAttribute("access_token"));
        return "로그아웃";
    }
}
