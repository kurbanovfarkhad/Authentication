package com.example.Authentication.controllers;

import com.example.Authentication.mapper.BookMapper;
import com.example.Authentication.model.Book;
import com.example.Authentication.model.Role;
import com.example.Authentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    @Autowired
    private BookMapper bookMapper;

    @RequestMapping(value = "/book/{id}")
    public String book(@PathVariable("id") String id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        try {
            user = (User) authentication.getPrincipal();
            model.addAttribute("user", user);
        } catch (Exception e) {
            System.out.println("guest");
        }

        Book book = bookMapper.findById(id);
        model.addAttribute("book", book);
        return "book";
    }

}
