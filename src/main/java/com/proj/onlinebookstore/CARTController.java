package com.proj.onlinebookstore;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CARTController {
    @Autowired
    CARTRepository cartRepository;

    @RequestMapping("/qcart")
    public String qcart(HttpSession httpSession) {
        List<CARTModel> cart = cartRepository.withUidCartQuery(httpSession.getAttribute("user").toString());
        Gson gson = new Gson();
        return gson.toJson(cart);
    }
}
