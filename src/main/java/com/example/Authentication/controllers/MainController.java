package com.example.Authentication.controllers;

import com.example.Authentication.mapper.BasketMapper;
import com.example.Authentication.mapper.BookMapper;
import com.example.Authentication.model.Basket;
import com.example.Authentication.model.Book;
import com.example.Authentication.model.Role;
import com.example.Authentication.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {
    Logger logger = Logger.getLogger(MainController.class);
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BasketMapper basketMapper;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        try {
            user = (User) authentication.getPrincipal();
            model.addAttribute("user", user);
            if (user.getAuthorities().contains(Role.ADMIN)) {
                return "redirect:/admin/";
            }
        } catch (Exception e) {
            System.out.println("guest");
        }

        List<Book> books = bookMapper.allBook();
        model.addAttribute("books", books);
        logger.info("main page is opened!!!");
        return "index";

    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration() {

        return "registration";
    }


    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String basket(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        try {
            user = (User) authentication.getPrincipal();
            model.addAttribute("user", user);
        } catch (Exception e) {
            System.out.println("guest");
        }


        List<Basket> basket = basketMapper.findByUser(Integer.toString(user.getId()));

        if (!basket.isEmpty()) {
            ArrayList<Book> books = new ArrayList<Book>();
            for (Basket i :
                    basket) {
                books.add(bookMapper.findById(Integer.toString(i.getBook_id())));
            }
            model.addAttribute("books", books);
        } else {
            model.addAttribute("error", "basket is empty");
        }
        return "basket";
    }

}
