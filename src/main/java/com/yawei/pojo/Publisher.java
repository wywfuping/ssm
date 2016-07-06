package com.yawei.pojo;


import java.io.Serializable;

public class Publisher implements Serializable{
    private static final long serialVersionUID = 143762183515498511L;
    private Integer id;
    private String pubname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPubname() {
        return pubname;
    }

    public void setPubname(String pubname) {
        this.pubname = pubname;
    }
}
