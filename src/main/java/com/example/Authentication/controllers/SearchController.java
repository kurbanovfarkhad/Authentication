package com.example.Authentication.controllers;

import com.example.Authentication.mapper.BookMapper;
import com.example.Authentication.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController{
    @Autowired
    BookMapper bookMapper;
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "page",required = false) String page, @RequestParam(value = "text",required = false)String text, Model model){

        if (text!=null) {
            text = "%" + text + "%";
            List<Book> books = bookMapper.findByCondition(text);
            if (books.isEmpty()){
                model.addAttribute("error", "NotFound");
            }else{
                model.addAttribute("books", books);
            }

        }
        return "search";
    }



//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public ResponseEntity<List<Book>> search(
//            @RequestParam(value = "text",required = false)String text) {
//
//        List<Book> books = bookMapper.findByCondition(text);
//        if (books.isEmpty()){
//            return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
//        }else{
//            return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
//        }
//    }
}
