package com.proj.onlinebookstore.Fun;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

public interface ItemFun {
    @RequestMapping("/saveitem1")
    Boolean save1(String oidorcid, String bid, Integer num);

    @RequestMapping("/saveitem2")
    Boolean save2(String bid, Integer num, HttpSession httpSession);

    @RequestMapping("/citem")
    Boolean change(String bid, Integer num, HttpSession httpSession);

    @RequestMapping("/ditem")
    String delete(String bid, HttpSession httpSession);

    @RequestMapping("/qitem")
    String qitem(String oidorcid);
}
