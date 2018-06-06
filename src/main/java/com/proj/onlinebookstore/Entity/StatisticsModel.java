package com.proj.onlinebookstore.Entity;

public class StatisticsModel {
    private String OrderID;
    private String BookName;
    private String Author;
    private String Username;
    private String Date;
    private Integer Num;
    private Integer Subtotal;

    public StatisticsModel(){
    }

    public StatisticsModel(String OrderID, String BookName, String Author, String Username, String Date, Integer Num, Integer Subtotal) {
        this.OrderID = OrderID;
        this.BookName = BookName;
        this.Author = Author;
        this.Username = Username;
        this.Date = Date;
        this.Num = Num;
        this.Subtotal = Subtotal;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        this.OrderID = orderID;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        this.BookName = bookName;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public Integer getNum() {
        return Num;
    }

    public void setNum(Integer num) {
        this.Num = num;
    }

    public Integer getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.Subtotal = subtotal;
    }
}
