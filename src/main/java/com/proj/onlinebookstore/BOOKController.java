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
    @Autowired
    ITEMRepository itemRepository;

    @RequestMapping("/savebook")
    public String save(String name, String author, String language, String published, Integer price, Integer stock) {
        if(bookRepository.withNALPBookQuery(name, author, language, published) != null)
            return "false";
        String uuid = UUID.randomUUID().toString();
        String[] idd = uuid.split("-");
        String id = idd[0] + idd[1] + idd[2];
        bookRepository.save(new BOOK(id, name, author, language, published, price, 0, stock));
        return "success";
    }

    @RequestMapping("/dbook")
    public String dbook(String id) {
        BOOK book = bookRepository.withIdBookQuery(id);
        if(book == null)
            return "null";
        List<ITEM> items = itemRepository.withBidITEMQuery(id);
        bookRepository.delete(book);
        for(ITEM item : items) {
            itemRepository.delete(item);
        }
        bookRepository.delete(book);
        return "success";
    }

    @RequestMapping("/qbook")
    public String qbook(String id) {
        BOOK book = bookRepository.withIdBookQuery(id);
        if(book == null)
            return "null";
        Gson gson = new Gson();
        return gson.toJson(book);
    }

    @RequestMapping("/cbook")
    public String cbook(String id, String name, String author, String language, String published, Integer price, Integer stock) {
        BOOK book = bookRepository.withIdBookQuery(id);
        book.setName(name);
        book.setAuthor(author);
        book.setLanguage(language);
        book.setPublished(published);
        book.setPrice(price);
        book.setStock(stock);
        bookRepository.save(book);
        return "success";
    }

    @RequestMapping("/allbook")
    public String allbook(){
        List<BOOK> books = bookRepository.findAll();
        Gson gson = new Gson();
        return gson.toJson(books);
    }
}