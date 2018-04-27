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
        userRepository.save(new USER(id, username, password, email, phonenumber, 1));
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
        return "login";
    }

    @RequestMapping("/userlogin")
    public String login(String username, String password, HttpSession httpSession) {
        USER user = userRepository.withUsernameAndPasswordUserQuery(username, password);
        if(user != null) {
            httpSession.setAttribute("user", user.getId());
            return user.getId();
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

    @RequestMapping("/alluser")
    public String alluser(){
        List<USER> users = userRepository.findAll();
        Gson gson = new Gson();
        return gson.toJson(users);
    }
}
