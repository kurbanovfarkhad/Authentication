package com.example.Authentication.mapper;

import com.example.Authentication.model.Role;
import com.example.Authentication.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Collection;
@Component
@Mapper
public interface UserMapper {
    String getByName = "select * from user where username = #{username}";
    String getRole = "select role from role where username = #{username}";
    @Select(getByName)
    @Results(value = {
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
}
