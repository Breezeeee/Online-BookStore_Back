package com.proj.onlinebookstore;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class BOOKController {
    @Autowired
    BOOKRepository bookRepository;

    @RequestMapping("/savebook")
    public Boolean save(String name, String author, String language, String published, Integer price, Integer sales, Integer stock) {
        String uuid = UUID.randomUUID().toString();
        String[] idd = uuid.split("-");
        String id = idd[0] + idd[1] + idd[2];
        bookRepository.save(new BOOK(id, name, author, language, published, price, sales, stock));
        return true;
    }

    @RequestMapping("/qbook")
    public String qbook(String id) {
        BOOK book = bookRepository.withIdBookQuery(id);
        Gson gson = new Gson();
        return gson.toJson(book);
    }

    @RequestMapping("/allbook")
    public String allbook(){
        List<BOOK> books = bookRepository.findAll();
        Gson gson = new Gson();
        return gson.toJson(books);
    }
}