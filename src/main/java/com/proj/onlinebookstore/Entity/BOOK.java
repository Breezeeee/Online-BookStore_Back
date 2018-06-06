package com.proj.onlinebookstore.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BOOK {
    @Id
    private String id;
    private String name;
    private String author;
    private String language;
    private String published;
    private Integer price;
    private Integer sales;
    private Integer stock;

    public BOOK() {
    }

    public BOOK(String id, String name, String author, String language, String published, Integer price, Integer sales, Integer stock) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.language = language;
        this.published = published;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }
}