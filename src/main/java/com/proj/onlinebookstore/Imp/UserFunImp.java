package com.proj.onlinebookstore.Imp;

import com.google.gson.Gson;
import com.proj.onlinebookstore.Entity.CART;
import com.proj.onlinebookstore.Entity.ITEM;
import com.proj.onlinebookstore.Entity.ORDERFORM;
import com.proj.onlinebookstore.Entity.USER;
import com.proj.onlinebookstore.Fun.SetUUID;
import com.proj.onlinebookstore.Fun.UserFun;
import com.proj.onlinebookstore.Repository.CARTRepository;
import com.proj.onlinebookstore.Repository.ITEMRepository;
import com.proj.onlinebookstore.Repository.ORDERFORMRepository;
import com.proj.onlinebookstore.Repository.USERRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserFunImp implements UserFun {
    @Autowired
    USERRepository userRepository;
    @Autowired
    CARTRepository cartRepository;
    @Autowired
    ORDERFORMRepository orderformRepository;
    @Autowired
    ITEMRepository itemRepository;
    @Autowired
    SetUUID setUUID;

    public String checkusername(String username) {
        if(userRepository.withUsernameUserQuery(username) != null)
            return "false";
        return "true";
    }

    public Boolean save(String username, String password, String email, Long phonenumber) {
        String id = setUUID.getUUID();
        userRepository.save(new USER(id, username, password, email, phonenumber, 1, false));
        String cid = setUUID.getUUID();
        cartRepository.save(new CART(cid, id));
        return true;
    }

    public String check(HttpSession httpSession) {
        if(httpSession.getAttribute("user") == null) {
            return "null";
        }
        USER user = userRepository.withIdUserQuery(httpSession.getAttribute("user").toString());
        if(user.getAdmin())
            return "admin";
        return "login";
    }

    public String login(String username, String password, HttpSession httpSession) {
        USER user = userRepository.withUsernameAndPasswordUserQuery(username, password);
        if (user != null) {
            if (user.getState() == 0)
                return "false";
            httpSession.setAttribute("user", user.getId());
            if (user.getAdmin())
                return "admin";
            return "success";
        }
        return "null";
    }

    public String logout(HttpSession httpSession) {
        httpSession.setAttribute("user", null);
        return "success";
    }

    public String quser(HttpSession httpSession) {
        USER user = userRepository.withIdUserQuery(httpSession.getAttribute("user").toString());
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    public Boolean cuser(String username, String password, String email, Long phonenumber) {
        USER user = userRepository.withUsernameUserQuery(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhonenumber(phonenumber);
        userRepository.save(user);
        return true;
    }

    public String bruser(String username, Integer state) {
        USER user = userRepository.withUsernameUserQuery(username);
        if(user == null)
            return "null";
        user.setState(state);
        userRepository.save(user);
        return "success";
    }

    public String duser(String username) {
        USER user = userRepository.withUsernameUserQuery(username);
        if(user == null)
            return "null";
        CART cart = cartRepository.withUidCidQuery(user.getId());
        List<ORDERFORM> orders = orderformRepository.findByUid(user.getId());
        List<ITEM> items1 = itemRepository.findByOidorcid(cart.getCid());
        for(ITEM item : items1) {
            itemRepository.delete(item);
        }
        for(ORDERFORM order : orders) {
            List<ITEM> items2 = itemRepository.findByOidorcid(order.getId());
            for(ITEM item : items2) {
                itemRepository.delete(item);
            }
            orderformRepository.delete(order);
        }
        cartRepository.delete(cart);
        userRepository.delete(user);
        return "success";
    }

    public String alluser(){
        List<USER> users = userRepository.findAll();
        Gson gson = new Gson();
        return gson.toJson(users);
    }
}
