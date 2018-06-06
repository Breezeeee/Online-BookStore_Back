package com.proj.onlinebookstore.Fun;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

public interface UserFun {
    @RequestMapping("/checkusername")
    String checkusername(String username);

    @RequestMapping("/saveuser")
    Boolean save(String username, String password, String email, Long phonenumber);

    @RequestMapping("/checkstate")
    String check(HttpSession httpSession);

    @RequestMapping("/userlogin")
    String login(String username, String password, HttpSession httpSession);

    @RequestMapping("/userlogout")
    String logout(HttpSession httpSession);

    @RequestMapping("/quser")
    String quser(HttpSession httpSession);

    @RequestMapping("/cuser")
    Boolean cuser(String username, String password, String email, Long phonenumber);

    @RequestMapping("/ban_release")
    String bruser(String username, Integer state);

    @RequestMapping("/duser")
    String duser(String username);

    @RequestMapping("/alluser")
    String alluser();
}
