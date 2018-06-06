package com.proj.onlinebookstore.Fun;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

public interface OrderformFun {
    @RequestMapping("/saveorder")
    String save(String address, HttpSession httpSession);

    @RequestMapping("/qorder1")
    String qorder1(HttpSession httpSession);

    @RequestMapping("/qorder2")
    String qorder2(String id);

    @RequestMapping("/qorder3")
    String qorder3(String id);

    @RequestMapping("/qorder4")
    String qorder4(String date1, String date2, String book, String author, HttpSession httpSession);

    @RequestMapping("/statistics")
    String Statistics(String book, String author, String username, String date1, String date2);
}
