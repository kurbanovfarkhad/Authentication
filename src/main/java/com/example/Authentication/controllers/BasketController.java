package com.example.Authentication.controllers;

import com.example.Authentication.mapper.BasketMapper;
import com.example.Authentication.model.Basket;
import com.example.Authentication.model.User;
import com.example.Authentication.services.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BasketController {
    @Autowired
    private BasketMapper basketMapper;
    @Autowired
    private MailSender mailSender;

    @RequestMapping(value = "/addtobasket", method = RequestMethod.POST)
    public ResponseEntity<String> addToBasket(@RequestBody Basket basket) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        try {
            user = (User) authentication.getPrincipal();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        basket.setUser_id(user.getId());
        basketMapper.addToBasket(basket);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/buybasket", method = RequestMethod.POST)
    public ResponseEntity<String> buy() {
        mailSender.send("ruacbwrj@yomail.info", "Hello", "Hello");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteFromBasket", method = RequestMethod.POST)
    public ResponseEntity<String> delete(@RequestBody Basket basket) {
        basketMapper.deleteFromBasket(basket.getBook_id());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
