package com.proj.onlinebookstore.Imp;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.proj.onlinebookstore.Entity.*;
import com.proj.onlinebookstore.Fun.OrderformFun;
import com.proj.onlinebookstore.Fun.SetUUID;
import com.proj.onlinebookstore.Repository.BOOKRepository;
import com.proj.onlinebookstore.Repository.CARTRepository;
import com.proj.onlinebookstore.Repository.ITEMRepository;
import com.proj.onlinebookstore.Repository.ORDERFORMRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderformFunImp implements OrderformFun {
    @Autowired
    ORDERFORMRepository orderRepository;
    @Autowired
    CARTRepository cartRepository;
    @Autowired
    ITEMRepository itemRepository;
    @Autowired
    BOOKRepository bookRepository;
    @Autowired
    SetUUID setUUID;

    public String save(String address, HttpSession httpSession) {
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String second = String.valueOf(calendar.get(Calendar.SECOND));
        if(month.length() == 1)
            month = "0" + month;
        if(day.length() == 1)
            day = "0" + day;
        if(hour.length() == 1)
            hour = "0" + hour;
        if(minute.length() == 1)
            minute = "0" + minute;
        if(second.length() == 1)
            second = "0" + second;
        String date = year + "-" + month + "-" + day  + " " + hour + ":" + minute + ":" + second;

        String id = setUUID.getUUID();

        List<CARTModel> cartitems = cartRepository.withUidCartQuery(httpSession.getAttribute("user").toString());
        Integer total = 0;
        for(CARTModel cartitem : cartitems) {
            total += cartitem.getPrice() * cartitem.getNum();
        }
        orderRepository.save(new ORDERFORM(id, total, date, address, httpSession.getAttribute("user").toString()));

        CART cart = cartRepository.withUidCidQuery(httpSession.getAttribute("user").toString());
        List<ITEM> items = itemRepository.findByOidorcid(cart.getCid());
        for(ITEM item : items) {
            item.setOidorcid(id);
            BOOK book = bookRepository.withIdBookQuery(item.getBid());
            book.setStock(book.getStock() - item.getNum());
            book.setSales(book.getSales() + item.getNum());
            bookRepository.save(book);
            itemRepository.save(item);
        }
        return id;
    }

    public String qorder1(HttpSession httpSession) {
        List<ORDERFORM> orderform = orderRepository.findByUid(httpSession.getAttribute("user").toString());
        Gson gson = new Gson();
        return gson.toJson(orderform);
    }

    public String qorder2(String id) {
        List<OrderModel> orderform = orderRepository.withOidOrderItemsQuery(id);
        Gson gson = new Gson();
        return gson.toJson(orderform);
    }

    public String qorder3(String id) {
        ORDERFORM orderform = orderRepository.withOidOrderQuery(id);
        Gson gson = new Gson();
        return gson.toJson(orderform);
    }

    public String qorder4(String date1, String date2, String book, String author, HttpSession httpSession) {
        if(book == null)
            book = "";
        if(author == null)
            author = "";
        List<ORDERFORM> orderform = orderRepository.findByUid(httpSession.getAttribute("user").toString());
        List<ORDERFORM> res = new ArrayList<>();
        for(ORDERFORM order : orderform) {
            Boolean success = true;
            if(order.getDate().compareTo(date1) < 0 || order.getDate().compareTo(date2) > 0)
                success = false;
            else {
                List<OrderModel> items = orderRepository.withOidOrderItemsQuery(order.getId());
                Boolean find = false;
                for (OrderModel item : items) {
                    if (item.getBname().contains(book) && item.getBauthor().contains(author))
                        find = true;
                }
                if (!find)
                    success = false;
            }
            if(success)
                res.add(order);
        }
        Gson gson = new Gson();
        return gson.toJson(res);
    }

    public String Statistics(String book, String author, String username, String date1, String date2) {
        if(book == null)
            book = "";
        if(author == null)
            author = "";
        if(username == null)
            username = "";
        List<StatisticsModel> stats = orderRepository.OrderStatistics();
        List<StatisticsModel> res = new ArrayList<>();
        for(StatisticsModel stat : stats) {
            Boolean success = true;
            if(stat.getDate().compareTo(date1) < 0 || stat.getDate().compareTo(date2) > 0)
                success = false;
            if(!stat.getBookName().contains(book) || !stat.getAuthor().contains(author) || !stat.getUsername().contains(username))
                success = false;
            if(success)
                res.add(stat);
        }
        Gson gson = new Gson();
        return gson.toJson(res);
    }
}
