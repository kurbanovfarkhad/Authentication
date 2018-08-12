package com.example.Authentication.controllers;

import com.example.Authentication.mapper.BookMapper;
import com.example.Authentication.model.Book;
import com.example.Authentication.model.Role;
import com.example.Authentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private BookMapper bookMapper;

    @RequestMapping("/")
    public String main(Model model){
        List<Book> books = bookMapper.allBook();
        model.addAttribute("books", books );

        return "admin/admin";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String addBook(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user",user);

        if (user.getAuthorities().contains(Role.ADMIN)){
            model.addAttribute("admin",user.getAuthorities().contains(Role.ADMIN));
        }
        return "admin/addbook";
    }

    private String addFile(MultipartFile file){
        String resultFilename = null;
        if(file!=null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            resultFilename = uuidFile + "." + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + "/" + resultFilename));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return resultFilename;
    }
    private void deleteFile(String fileName){
        File file = new File(uploadPath+"/"+fileName);
        try{
            file.delete();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public ResponseEntity<String> add(
            @RequestParam(value = "title",required = false)String title,
            @RequestParam(value = "description",required = false)String description,
            @RequestParam(value = "picture",required = false)MultipartFile file){
        Book book = new Book();
        book.setPicture(addFile(file));
        book.setTitle(title);
        book.setDescription(description);

        bookMapper.addBook(book);

        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RequestMapping(value = "/edit/{id}")
    public String editPage(@PathVariable("id") String id, Model model){
        Book book = bookMapper.findById(id);
        model.addAttribute("book", book);
        return "admin/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> edit(@PathVariable("id") String id, Model model
            ,@RequestParam(value = "title",required = false)String title,
                                       @RequestParam(value = "description",required = false)String description,
                                       @RequestParam(value = "picture",required = false)MultipartFile file){
        Book book = bookMapper.findById(id);
        if (!file.isEmpty()){
            deleteFile(book.getPicture());
        }
        book.setDescription(description);
        book.setTitle(title);
        book.setPicture(addFile(file));
        bookMapper.updateBook(book);
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", required = true) String id){

        deleteFile(bookMapper.findById(id).getPicture());
        bookMapper.deleteBook(id);

        return "redirect:/admin/";
    }

}
