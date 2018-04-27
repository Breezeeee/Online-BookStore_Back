package com.proj.onlinebookstore;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CART {
    @Id
    private String cid;
    private String uid;

    public CART() {
    }

    public CART(String cid, String uid) {
        this.cid = cid;
        this.uid = uid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
