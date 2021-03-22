package com.toy.blogcode.springbootaws.common.service;

import com.toy.blogcode.springbootaws.model.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public Optional<User> selectUserByNo(Long userNo) {
        User user = new User();

        return Optional.ofNullable(user);
    }
}
