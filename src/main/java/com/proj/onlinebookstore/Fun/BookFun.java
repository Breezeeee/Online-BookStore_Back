package com.proj.onlinebookstore.Fun;

import org.springframework.web.bind.annotation.RequestMapping;

public interface BookFun {
    @RequestMapping("/savebook")
    String save(String name, String author, String language, String published, Integer price, Integer stock);

    @RequestMapping("/dbook")
    String dbook(String id);

    @RequestMapping("/qbook")
    String qbook(String id);

    @RequestMapping("/cbook")
    String cbook(String id, String name, String author, String language, String published, Integer price, Integer stock);

    @RequestMapping("/allbook")
    String allbook();
}
