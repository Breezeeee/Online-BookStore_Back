package com.proj.onlinebookstore.Fun;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

public interface CartFun {
    @RequestMapping("/qcart")
    String qcart(HttpSession httpSession);

    @RequestMapping("/checkcart")
    String checkcart(HttpSession httpSession);
}
