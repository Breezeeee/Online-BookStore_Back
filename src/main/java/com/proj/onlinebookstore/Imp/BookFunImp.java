package com.proj.onlinebookstore.Imp;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import com.proj.onlinebookstore.Entity.BOOK;
import com.proj.onlinebookstore.Fun.BookFun;
import com.proj.onlinebookstore.Entity.ITEM;
import com.proj.onlinebookstore.Repository.BOOKRepository;
import com.proj.onlinebookstore.Fun.SetUUID;
import com.proj.onlinebookstore.Repository.ITEMRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookFunImp implements BookFun {
    @Autowired
    BOOKRepository bookRepository;
    @Autowired
    ITEMRepository itemRepository;
    @Autowired
    private SetUUID setUUID;

    public String save(String name, String author, String language, String published, Integer price, Integer stock) {
        if(bookRepository.withNALPBookQuery(name, author, language, published) != null)
            return "false";
        String id = setUUID.getUUID();
        bookRepository.save(new BOOK(id, name, author, language, published, price, 0, stock));
        return "success";
    }

    public String dbook(String id) {
        BOOK book = bookRepository.withIdBookQuery(id);
        if(book == null)
            return "null";
        List<ITEM> items = itemRepository.withBidITEMQuery(id);
        for(ITEM item : items) {
            itemRepository.delete(item);
        }
        bookRepository.delete(book);
        return "success";
    }

    public String qbook(String id) {
        BOOK book = bookRepository.withIdBookQuery(id);
        if(book == null)
            return "null";
        Gson gson = new Gson();
        return gson.toJson(book);
    }

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

    public String allbook(){
        List<BOOK> books = bookRepository.findAll();
        Gson gson = new Gson();
        return gson.toJson(books);
    }
}
