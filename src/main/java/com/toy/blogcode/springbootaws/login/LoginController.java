package com.toy.blogcode.springbootaws.login;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class LoginController {
    String kakaoAuthUrl = "https://kauth.kakao.com";
    String kakaoApiKey = "e960a5e5eef1a420a189423849ac8960";
    String redirectURI = "http://localhost:8080/login/oauth_kakao";

    @GetMapping(value = "/login")
    public ModelAndView login(ModelAndView mav) {
        mav.setViewName("login/login");
        return mav;
    }

    // 카카오 로그인창 호출
    @RequestMapping(value = "/login/getKakaoAuthUrl")
    public @ResponseBody String getKakaoAuthUrl(HttpServletRequest request) throws Exception {
        String reqUrl = kakaoAuthUrl + "/oauth/authorize?client_id=" + kakaoApiKey + "&redirect_uri="+ redirectURI + "&response_type=code";
        return reqUrl;
    }


    // 카카오 연동정보 조회
    @RequestMapping(value = "/login/oauth_kakao")
    public String oauthKakao(@RequestParam(value = "code", required = false) String code) throws Exception{
        String userID;

        JsonNode accessToken = getAccessToken(code);

        if (accessToken != null) {
            //System.out.println("accessToken : " + accessToken);
            userID = getUserInfo(accessToken.get("access_token").asText());

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

    // 카카오 로그인 access_token 리턴
    public JsonNode getAccessToken(String code) {
        final String reqUrl = kakaoAuthUrl + "/oauth/token"; // Host
        final List<NameValuePair> postParams = new ArrayList<NameValuePair>();

        postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
        postParams.add(new BasicNameValuePair("client_id", kakaoApiKey));
        postParams.add(new BasicNameValuePair("redirect_uri", redirectURI));
        postParams.add(new BasicNameValuePair("code", code));

        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(reqUrl);

        JsonNode returnNode = null;

        try {
            post.setEntity(new UrlEncodedFormEntity(postParams));

            final HttpResponse response = client.execute(post);
            final int responseCode = response.getStatusLine().getStatusCode();

            if (responseCode == 200) {
                ObjectMapper mapper = new ObjectMapper();
                returnNode = mapper.readTree(response.getEntity().getContent());
            }
            else {
                returnNode = null;
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }

        return returnNode;
    }

    public static String getUserInfo (String access_Token) {
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        String userID = "";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line = "";
                String result = "";

                while ((line = br.readLine()) != null) {
                    result += line;
                }
                //System.out.println("response body : " + result);

                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = new HashMap<String, Object>();

                map = mapper.readValue(result, Map.class);
                userID = map.get("id").toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userID;
    }
}
