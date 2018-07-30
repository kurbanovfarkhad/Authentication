package com.example.Authentication.persistance;

import com.example.Authentication.mapper.UserMapper;
import com.example.Authentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {
    @Autowired
    private UserMapper userMapper;

    public UserDao(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    public User findByUserName(String username) {
        User user = userMapper.findByName(username);
        user.setAuthorities(userMapper.getRole(username));
        return user;
    }
}
