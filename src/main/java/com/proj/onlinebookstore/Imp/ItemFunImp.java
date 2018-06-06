package com.proj.onlinebookstore.Imp;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;

import com.proj.onlinebookstore.Entity.BOOK;
import com.proj.onlinebookstore.Entity.CART;
import com.proj.onlinebookstore.Entity.ITEM;
import com.proj.onlinebookstore.Fun.ItemFun;
import com.proj.onlinebookstore.Fun.SetUUID;
import com.proj.onlinebookstore.Repository.BOOKRepository;
import com.proj.onlinebookstore.Repository.CARTRepository;
import com.proj.onlinebookstore.Repository.ITEMRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemFunImp implements ItemFun {
    @Autowired
    ITEMRepository itemRepository;
    @Autowired
    CARTRepository cartRepository;
    @Autowired
    BOOKRepository bookRepository;
    @Autowired
    SetUUID setUUID;

    public Boolean save1(String oidorcid, String bid, Integer num) {
        String id = setUUID.getUUID();
        itemRepository.save(new ITEM(id, oidorcid, bid, num));
        return true;
    }

    public Boolean save2(String bid, Integer num, HttpSession httpSession) {
        CART cart = cartRepository.withUidCidQuery(httpSession.getAttribute("user").toString());
        ITEM item = itemRepository.withCidAndBidItemQuery(cart.getCid(), bid);
        if(item != null) {
            BOOK book = bookRepository.withIdBookQuery(bid);
            Integer total_num = item.getNum() + num;
            Integer stock = book.getStock();
            if(total_num > stock)
                total_num = stock;
            item.setNum(total_num);
            itemRepository.save(item);
        }
        else {
            String id = setUUID.getUUID();
            itemRepository.save(new ITEM(id, cart.getCid(), bid, num));
        }
        return true;
    }

    public Boolean change(String bid, Integer num, HttpSession httpSession) {
        CART cart = cartRepository.withUidCidQuery(httpSession.getAttribute("user").toString());
        ITEM item = itemRepository.withCidAndBidItemQuery(cart.getCid(), bid);
        item.setNum(num);
        itemRepository.save(item);
        return true;
    }

    public String delete(String bid, HttpSession httpSession) {
        CART cart = cartRepository.withUidCidQuery(httpSession.getAttribute("user").toString());
        ITEM item = itemRepository.withCidAndBidItemQuery(cart.getCid(), bid);
        itemRepository.delete(item);
        return "success";
    }

    public String qitem(String oidorcid) {
        List<ITEM> item = itemRepository.findByOidorcid(oidorcid);
        Gson gson = new Gson();
        return gson.toJson(item);
    }
}
