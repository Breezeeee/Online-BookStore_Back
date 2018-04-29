package com.proj.onlinebookstore;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@RestController
public class USERController {
    @Autowired
    USERRepository userRepository;
    @Autowired
    CARTRepository cartRepository;
    @Autowired
    ORDERFORMRepository orderformRepository;
    @Autowired
    ITEMRepository itemRepository;

    @RequestMapping("/checkusername")
    public String checkusername(String username) {
        if(userRepository.withUsernameUserQuery(username) != null)
            return "false";
        return "true";
    }

    @RequestMapping("/saveuser")
    public Boolean save(String username, String password, String email, Long phonenumber) {
        String uuid = UUID.randomUUID().toString();
        String[] idd = uuid.split("-");
        String id = idd[0] + idd[1] + idd[2];
        userRepository.save(new USER(id, username, password, email, phonenumber, 1, false));
        uuid = UUID.randomUUID().toString();
        idd = uuid.split("-");
        String cid = idd[0] + idd[1] + idd[2];
        cartRepository.save(new CART(cid, id));
        return true;
    }

    @RequestMapping("/checkstate")
    public String check(HttpSession httpSession) {
        if(httpSession.getAttribute("user") == null) {
            return "null";
        }
        USER user = userRepository.withIdUserQuery(httpSession.getAttribute("user").toString());
        if(user.getAdmin())
            return "admin";
        return "login";
    }

    @RequestMapping("/userlogin")
    public String login(String username, String password, HttpSession httpSession) {
        USER user = userRepository.withUsernameAndPasswordUserQuery(username, password);
        if(user != null) {
            if(user.getState() == 0)
                return "false";
            httpSession.setAttribute("user", user.getId());
            if(user.getAdmin())
                return "admin";
            return "success";
        }
        return "null";
    }

    @RequestMapping("/userlogout")
    public String logout(HttpSession httpSession) {
        httpSession.setAttribute("user", null);
        return "success";
    }

    @RequestMapping("/quser")
    public String quser(HttpSession httpSession) {
        USER user = userRepository.withIdUserQuery(httpSession.getAttribute("user").toString());
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    @RequestMapping("/cuser")
    public Boolean cuser(String username, String password, String email, Long phonenumber) {
        USER user = userRepository.withUsernameUserQuery(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhonenumber(phonenumber);
        userRepository.save(user);
        return true;
    }

    @RequestMapping("/ban_release")
    public String bruser(String username, Integer state) {
        USER user = userRepository.withUsernameUserQuery(username);
        if(user == null)
            return "null";
        user.setState(state);
        userRepository.save(user);
        return "success";
    }

    @RequestMapping("/duser")
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

    @RequestMapping("/alluser")
    public String alluser(){
        List<USER> users = userRepository.findAll();
        Gson gson = new Gson();
        return gson.toJson(users);
    }
}
