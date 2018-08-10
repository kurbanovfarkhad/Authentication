package com.example.Authentication.controllers;

import com.example.Authentication.mapper.BookMapper;
import com.example.Authentication.model.Book;
import com.example.Authentication.model.Role;
import com.example.Authentication.model.User;
import com.example.Authentication.services.MailSender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class MainController {
    Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private MailSender mailSender;

    public void test(){
        mailSender.send("ruacbwrj@yomail.info", "Hello", "Hello");
    }

    private void aboutUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("userName",user.getUsername() );

        if (user.getAuthorities().contains(Role.ADMIN)){
            model.addAttribute("admin",user.getAuthorities().contains(Role.ADMIN));
        }
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model){
        aboutUser(model);
        return "registration";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String addBook(Model model){
        aboutUser(model);
        return "addbook";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model){
        List<Book> books = bookMapper.allBook();
        aboutUser(model);
        model.addAttribute("books", books );

        logger.info("main page is opened!!!");
        return "index";
    }

}
