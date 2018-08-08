package com.example.Authentication.controllers;

import com.example.Authentication.mapper.UserMapper;
import com.example.Authentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class RegistrationController {
    @Autowired
    UserMapper userMapper;


    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public ResponseEntity<User> update(@RequestBody User user){

        User userFromDb = userMapper.findByName(user.getUsername());
        if (userFromDb!=null){
            return new ResponseEntity<User>(user, HttpStatus.CONFLICT);
        }else{
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userMapper.addUser(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
    }
}
