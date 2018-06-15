package com.proj.onlinebookstore.Imp;

import com.google.gson.Gson;

import com.proj.onlinebookstore.Entity.BookInfo;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.proj.onlinebookstore.Entity.BOOK;
import com.proj.onlinebookstore.Fun.BookFun;
import com.proj.onlinebookstore.Entity.ITEM;
import com.proj.onlinebookstore.Repository.BOOKRepository;
import com.proj.onlinebookstore.Fun.SetUUID;
import com.proj.onlinebookstore.Repository.ITEMRepository;
import com.proj.onlinebookstore.Repository.BookInfoRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookFunImp implements BookFun {
    @Autowired
    BOOKRepository bookRepository;
    @Autowired
    ITEMRepository itemRepository;
    @Autowired
    private SetUUID setUUID;
    @Autowired
    private BookInfoRepository bookInfoRepository;

    public String save(String name, String author, String language, String published, Integer price, Integer stock) {
        if(bookRepository.withNALPBookQuery(name, author, language, published) != null)
            return "false";
        String id = setUUID.getUUID();
        bookRepository.save(new BOOK(id, name, author, language, published, price, 0, stock));
        return "success";
    }

    public String savecover(String bid, String cover) {
        BookInfo bookinfo = bookInfoRepository.findByBid(bid);
        if(bookinfo == null) {
            bookinfo = new BookInfo();
            bookinfo.setBid(bid);
            bookinfo.setCover(cover);
            bookinfo.setComments(null);
        }
        else {
            bookinfo.setCover(cover);
        }
        bookInfoRepository.save(bookinfo);
        return "success";
    }

    public String savecomment(String bid, String comment) {
        BookInfo bookinfo = bookInfoRepository.findByBid(bid);
        if(bookinfo == null) {
            bookinfo = new BookInfo();
            bookinfo.setBid(bid);
            bookinfo.setCover(null);
            List<String> comments = new ArrayList<>();
            comments.add(comment);
            bookinfo.setComments(comments);
        }
        else {
            if(bookinfo.getComments() == null) {
                List<String> comments = new ArrayList<>();
                comments.add(comment);
                bookinfo.setComments(comments);
            }
            else {
                List<String> comments = bookinfo.getComments();
                comments.add(comment);
                bookinfo.setComments(comments);
            }
        }
        bookInfoRepository.save(bookinfo);
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

    public String qcover(String id) {
        BookInfo bookinfo = bookInfoRepository.findByBid(id);
        if(bookinfo == null)
            return "null";
        return bookinfo.getCover();
    }

    public String qcomments(String id) {
        BookInfo bookinfo = bookInfoRepository.findByBid(id);
        if(bookinfo == null)
            return "null";
        List<String> comments = bookinfo.getComments();
        StringBuilder buf = new StringBuilder();
        buf.append("{\"comments\":[");
        for(String comment : comments) {
            buf.append("\"");
            buf.append(comment);
            buf.append("\",");
        }
        buf.deleteCharAt(buf.length()-1);
        buf.append("]}");
        return buf.toString();
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
