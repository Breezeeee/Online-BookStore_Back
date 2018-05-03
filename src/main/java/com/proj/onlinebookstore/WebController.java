package com.proj.onlinebookstore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class WebController {
    @RequestMapping(value = "/", method= RequestMethod.GET)
    public String jump1() {
        return "index.html";
    }
    @RequestMapping(value = "/booklist", method= RequestMethod.GET)
    public String jump2() {
        return "index.html";
    }
    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public String jump3() {
        return "index.html";
    }
    @RequestMapping(value = "/profile", method= RequestMethod.GET)
    public String jump4() {
        return "index.html";
    }
    @RequestMapping(value = "/cart", method= RequestMethod.GET)
    public String jump5() {
        return "index.html";
    }
    @RequestMapping(value = "/bookinfo", method= RequestMethod.GET)
    public String jump6() {
        return "index.html";
    }
    @RequestMapping(value = "/signup", method= RequestMethod.GET)
    public String jump7() {
        return "index.html";
    }
    @RequestMapping(value = "/createorder", method= RequestMethod.GET)
    public String jump8() {
        return "index.html";
    }
    @RequestMapping(value = "/orderlist", method= RequestMethod.GET)
    public String jump9() {
        return "index.html";
    }
    @RequestMapping(value = "/orderinfo", method= RequestMethod.GET)
    public String jump10() {
        return "index.html";
    }
    @RequestMapping(value = "/admin", method= RequestMethod.GET)
    public String jump11() {
        return "index.html";
    }
}