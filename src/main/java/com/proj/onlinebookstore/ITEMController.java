package com.proj.onlinebookstore;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@RestController
public class ITEMController {
    @Autowired
    ITEMRepository itemRepository;
    @Autowired
    CARTRepository cartRepository;
    @Autowired
    BOOKRepository bookRepository;

    @RequestMapping("/saveitem1")
    public Boolean save1(String oidorcid, String bid, Integer num) {
        String uuid = UUID.randomUUID().toString();
        String[] idd = uuid.split("-");
        String id = idd[0] + idd[1] + idd[2];
        itemRepository.save(new ITEM(id, oidorcid, bid, num));
        return true;
    }

    @RequestMapping("/saveitem2")
    public Boolean save2(String bid, Integer num, HttpSession httpSession) {
        CART cart = cartRepository.withUidCidQuery(httpSession.getAttribute("user").toString());
        ITEM item = itemRepository.withCidAndBidItemQuery(cart.getCid(), bid);
        if(item != null) {
            BOOK book = bookRepository.withIdBookQuery(bid);
            Integer total_num = item.getNum() + num;
            Integer stock = book.getStock();
            if(total_num > stock)
                total_num = stock;
            item.setNum(total_num);
            itemRepository.save(item);
        }
        else {
            String uuid = UUID.randomUUID().toString();
            String[] idd = uuid.split("-");
            String id = idd[0] + idd[1] + idd[2];
            itemRepository.save(new ITEM(id, cart.getCid(), bid, num));
        }
        return true;
    }

    @RequestMapping("/citem")
    public Boolean change(String bid, Integer num, HttpSession httpSession) {
        CART cart = cartRepository.withUidCidQuery(httpSession.getAttribute("user").toString());
        ITEM item = itemRepository.withCidAndBidItemQuery(cart.getCid(), bid);
        item.setNum(num);
        itemRepository.save(item);
        return true;
    }

    @RequestMapping("/ditem")
    public String delete(String bid, HttpSession httpSession) {
        CART cart = cartRepository.withUidCidQuery(httpSession.getAttribute("user").toString());
        ITEM item = itemRepository.withCidAndBidItemQuery(cart.getCid(), bid);
        itemRepository.delete(item);
        return "success";
    }

    @RequestMapping("/qitem")
    public String qitem(String oidorcid) {
        List<ITEM> item = itemRepository.findByOidorcid(oidorcid);
        Gson gson = new Gson();
        return gson.toJson(item);
    }
}
