package com.toy.blogcode.springbootaws.login.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
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
//import org.springframework.beans.factory.annotation.Value;

@PropertySource("classpath:/app.properties")
@Repository
public class KakaoService {

    @Value("${app.kakaoAuthUrl}")
    private String kakaoAuthUrl;

    @Value("${app.kakaoApiKey}")
    private String kakaoApiKey;

    @Value("${app.redirectURI}")
    private String redirectURI;

    @Value("${app.getUserInfoUrl}")
    private String getUserInfoUrl;

    @Value("${app.kakaoLogoutUrl}")
    private String kakaoLogoutUrl;

    // 카카오 로그인창 호출
    public @ResponseBody String getKakaoAuthUrl(HttpServletRequest request) throws Exception {
        System.out.println("kakaoAuthUrl:" + kakaoAuthUrl);
        System.out.println("kakaoApiKey:" + kakaoApiKey);
        System.out.println("redirectURI:" + redirectURI);

        String reqUrl = kakaoAuthUrl + "/oauth/authorize?client_id=" + kakaoApiKey + "&redirect_uri="+ redirectURI + "&response_type=code";
        return reqUrl;
    }

    // 카카오 로그인 access_token 리턴
    public JsonNode getAccessToken(String code) {
        final String reqUrl = kakaoAuthUrl + "/oauth/token";
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

            System.out.println("response : " + response);

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

    public String getUserInfo (String access_Token) {
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = getUserInfoUrl;
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

    public void kakaoLogout(String access_Token) {
        String reqURL = kakaoLogoutUrl;

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String result = "";
            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
