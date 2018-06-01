package com.proj.onlinebookstore;

public class CARTModel {
    private String bname;
    private String author;
    private String bid;
    private Integer price;
    private Integer num;
    private Integer stock;

    public CARTModel() {
    }

    public CARTModel(String bname, String author, String bid, Integer price, Integer num, Integer stock){
        this.bname = bname;
        this.author = author;
        this.bid = bid;
        this.price = price;
        this.num = num;
        this.stock = stock;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }
}
