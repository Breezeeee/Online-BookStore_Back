package com.proj.onlinebookstore.Fun;

import org.springframework.web.bind.annotation.RequestMapping;

public interface BookFun {
    @RequestMapping("/savebook")
    String save(String name, String author, String language, String published, Integer price, Integer stock);

    @RequestMapping("/savecover")
    String savecover(String bid, String cover);

    @RequestMapping("/savecomment")
    String savecomment(String bid, String comment);

    @RequestMapping("/dbook")
    String dbook(String id);

    @RequestMapping("/qbook")
    String qbook(String id);

    @RequestMapping("/qcover")
    String qcover(String id);

    @RequestMapping("/qcomments")
    String qcomments(String id);

    @RequestMapping("/cbook")
    String cbook(String id, String name, String author, String language, String published, Integer price, Integer stock);

    @RequestMapping("/allbook")
    String allbook();
}
