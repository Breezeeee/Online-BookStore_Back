package com.proj.onlinebookstore.Entity;

import javax.persistence.*;

@Entity
public class ORDERFORM {
    @Id
    private String id;
    private Integer total;
    private String date;
    private String address;
    private String uid;

    public ORDERFORM() {
    }

    public ORDERFORM(String id, Integer total, String date, String address, String uid) {
        this.id = id;
        this.total = total;
        this.date = date;
        this.address = address;
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
