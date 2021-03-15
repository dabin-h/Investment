package com.toy.blogcode.springbootaws.post.service;

import com.toy.blogcode.springbootaws.post.dto.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    final private SqlSession sqlSession;

    public UserService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Optional<User> selectUserById(String userId) {
        User user = sqlSession.selectOne("user.selectUserById", userId);

        return Optional.ofNullable(user);
    }

    public Optional<User> selectUserByNo(Long userNo) {
        User user = sqlSession.selectOne("user.selectUserByNo", userNo);

        return Optional.ofNullable(user);
    }

    public Optional<User> selectProviderUser(String userId, String provider) {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("userId", userId);
        hashMap.put("provider", provider);
        hashMap.put("column", "provider");

        System.out.println("testtesttesttesttesttesttest");

        User user = sqlSession.selectOne("user.selectProviderUser", hashMap);

        return Optional.ofNullable(user);
    }

    public Optional<List<User>> selectUsers() {

        List<User> userList = sqlSession.selectList("user.selectUsers");

        return Optional.ofNullable(userList);
    }

    public void deleteUser(String userId) {
        sqlSession.delete("user.deleteUser", userId);
    }

    public void insertUser(User user) {
        sqlSession.insert("user.insertUser", user);
    }

    public void updateUser(User user) {
        sqlSession.update("user.updateUser", user);
    }
}
