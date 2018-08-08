package com.example.Authentication.mapper;

import com.example.Authentication.model.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface BookMapper {
    String addBook = "insert into book(picture, title, description) values (#{picture},#{title},#{description})";
    String allBook = "select * from book";
    String findByName = "select *from book where title = #{title}";
    String findById = "select * from book where id = #{id}";
    String findByCondition = "SELECT * FROM book WHERE title LIKE #{text}";
    String deleteBook = "DELETE from book WHERE ID = #{id}";
    @Select(allBook)
    List<Book> allBook();

    @Select(findByName)
    Book findByName(String book);

    @Select(findById)
    Book findById(String id);

    @Insert(addBook)
    void addBook(Book book);

    @Select(findByCondition)
    List<Book> findByCondition(String text);
    @Delete(deleteBook)
    void deleteBook(String id);
}
