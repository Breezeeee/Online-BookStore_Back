package com.proj.onlinebookstore;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;
import java.util.Calendar;

@RestController
public class ORDERFORMController {
    @Autowired
    ORDERFORMRepository orderRepository;
    @Autowired
    CARTRepository cartRepository;
    @Autowired
    ITEMRepository itemRepository;
    @Autowired
    BOOKRepository bookRepository;

    @RequestMapping("/saveorder")
    public String save(String address, HttpSession httpSession) {
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String second = String.valueOf(calendar.get(Calendar.SECOND));
        if(month.length() == 1)
            month = "0" + month;
        if(day.length() == 1)
            day = "0" + day;
        if(hour.length() == 1)
            hour = "0" + hour;
        if(minute.length() == 1)
            minute = "0" + minute;
        if(second.length() == 1)
            second = "0" + second;
        String date = year + "-" + month + "-" + day  + " " + hour + ":" + minute + ":" + second;

        String uuid = UUID.randomUUID().toString();
        String[] idd = uuid.split("-");
        String id = idd[0] + idd[1] + idd[2];

        List<CARTModel> cartitems = cartRepository.withUidCartQuery(httpSession.getAttribute("user").toString());
        Integer total = 0;
        for(CARTModel cartitem : cartitems) {
            total += cartitem.getPrice() * cartitem.getNum();
        }
        orderRepository.save(new ORDERFORM(id, total, date, address, httpSession.getAttribute("user").toString()));

        CART cart = cartRepository.withUidCidQuery(httpSession.getAttribute("user").toString());
        List<ITEM> items = itemRepository.findByOidorcid(cart.getCid());
        for(ITEM item : items) {
            item.setOidorcid(id);
            BOOK book = bookRepository.withIdBookQuery(item.getBid());
            book.setStock(book.getStock() - item.getNum());
            book.setSales(book.getSales() + item.getNum());
            bookRepository.save(book);
            itemRepository.save(item);
        }
        return id;
    }

    @RequestMapping("/qorder1")
    public String qorder1(HttpSession httpSession) {
        List<ORDERFORM> orderform = orderRepository.findByUid(httpSession.getAttribute("user").toString());
        Gson gson = new Gson();
        return gson.toJson(orderform);
    }

    @RequestMapping("/qorder2")
    public String qorder2(String id) {
        List<OrderModel> orderform = orderRepository.withOidOrderItemsQuery(id);
        Gson gson = new Gson();
        return gson.toJson(orderform);
    }

    @RequestMapping("/qorder3")
    public String qorder3(String id) {
        ORDERFORM orderform = orderRepository.withOidOrderQuery(id);
        Gson gson = new Gson();
        return gson.toJson(orderform);
    }
}
