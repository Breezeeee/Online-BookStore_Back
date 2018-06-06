package com.proj.onlinebookstore.Entity;

public class OrderModel {
    private String bname;
    private String bauthor;
    private Integer price;
    private Integer num;
    private Integer subtotal;

    public OrderModel(){
    }

    public OrderModel(String bname, String bauthor, Integer price, Integer num, Integer subtotal) {
        this.bname = bname;
        this.bauthor = bauthor;
        this.price = price;
        this.num = num;
        this.subtotal = subtotal;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBauthor() {
        return bauthor;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }
}
