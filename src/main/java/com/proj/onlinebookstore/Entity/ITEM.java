package com.proj.onlinebookstore;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ITEM {
    @Id
    private String id;
    private String oidorcid;
    private String bid;
    private Integer num;

    public ITEM() {
    }

    public ITEM(String id, String oidorcid, String bid, Integer num) {
        this.id = id;
        this.oidorcid = oidorcid;
        this.bid = bid;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOidorcid() {
        return oidorcid;
    }

    public void setOidorcid(String oidorcid) {
        this.oidorcid = oidorcid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
