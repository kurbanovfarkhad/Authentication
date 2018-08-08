package com.example.Authentication.controllers;

import com.example.Authentication.mapper.BookMapper;
import com.example.Authentication.model.Role;
import com.example.Authentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {



    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(){
        return "registration";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String addBook(){
        return "addbook";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("userName",user.getUsername() );

        if (user.getAuthorities().contains(Role.ADMIN)){
            model.addAttribute("admin",user.getAuthorities().contains(Role.ADMIN));
        }

        return "index";
    }

}
