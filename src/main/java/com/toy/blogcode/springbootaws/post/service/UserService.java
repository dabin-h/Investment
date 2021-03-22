package com.toy.blogcode.springbootaws.post.service;

import com.toy.blogcode.springbootaws.post.domain.User;
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

    public Optional<User> selectUserByNo(Long userNo) {
        User user = sqlSession.selectOne("user.selectUserByNo", userNo);

        return Optional.ofNullable(user);
    }
}
