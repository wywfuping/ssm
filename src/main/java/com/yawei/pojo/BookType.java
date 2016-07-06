package com.yawei.pojo;


import java.io.Serializable;

public class BookType implements Serializable{
    private static final long serialVersionUID = 70856214260025289L;
    private Integer id;
    private String booktype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }
}
