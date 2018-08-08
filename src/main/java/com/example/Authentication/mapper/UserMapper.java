package com.example.Authentication.mapper;

import com.example.Authentication.model.Role;
import com.example.Authentication.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Collection;
@Component
@Mapper
public interface UserMapper {
    String getByName = "select * from user where username = #{username}";
    String getRole = "select role from role where user_id = (select id from user where username = #{username})";
    String addUser = "insert into user(username,password) values(#{username},#{password})";
    String addRole = "insert into role(user_id,role) values(#{user_id}, #{role})";
    @Select(getByName)
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "accountNonExpired", column = "accountNonExpired"),
            @Result(property = "accountNonLocked", column = "accountNonLocked"),
            @Result(property = "credentialsNonExpired", column = "credentialsNonExpired"),
            @Result(property = "enabled", column = "enabled")
    })
    User findByName(String username);
    @Select(getRole)
    Collection<Role> getRole(String username);

    @Insert(addUser)
    void addUser(User user);

    @Insert(addRole)
    void addRole(@Param("user_id")String userId,@Param("role")String role);
}
