package com.proj.onlinebookstore.Imp;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.List;

import com.proj.onlinebookstore.Entity.CART;
import com.proj.onlinebookstore.Entity.CARTModel;
import com.proj.onlinebookstore.Entity.ITEM;
import com.proj.onlinebookstore.Fun.CartFun;
import com.proj.onlinebookstore.Repository.CARTRepository;
import com.proj.onlinebookstore.Repository.ITEMRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartFunImp implements CartFun {
    @Autowired
    CARTRepository cartRepository;
    @Autowired
    ITEMRepository itemRepository;

    public String qcart(HttpSession httpSession) {
        List<CARTModel> cart = cartRepository.withUidCartQuery(httpSession.getAttribute("user").toString());
        Gson gson = new Gson();
        return gson.toJson(cart);
    }

    public String checkcart(HttpSession httpSession) {
        CART cart = cartRepository.withUidCidQuery(httpSession.getAttribute("user").toString());
        List<ITEM> item = itemRepository.withCidItemQuery(cart.getCid());
        if(item.isEmpty())
            return "false";
        return "true";
    }
}
