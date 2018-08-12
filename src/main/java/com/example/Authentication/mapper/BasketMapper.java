package com.example.Authentication.mapper;

import com.example.Authentication.model.Basket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BasketMapper {

    String addById = "insert into basket (book_id,user_id) values(#{book_id},#{user_id})";
    String findByUser = "Select * from basket where user_id = #{id}";
    String deleteFromBasket = "DELETE from basket WHERE book_id = #{id}";
    @Insert(addById)
    void addToBasket(Basket basket);

    @Select(findByUser)
    List<Basket> findByUser(String id);
    @Delete(deleteFromBasket)
    void deleteFromBasket(@Param("id") int id);
}
