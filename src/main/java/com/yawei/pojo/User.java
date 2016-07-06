package com.yawei.pojo;


import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = -8889896647100355614L;
    private Integer id;
    private String username;
    private String password;
    private String lastaccesstime;
    private String lastaccessip;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastaccesstime() {
        return lastaccesstime;
    }

    public void setLastaccesstime(String lastaccesstime) {
        this.lastaccesstime = lastaccesstime;
    }

    public String getLastaccessip() {
        return lastaccessip;
    }

    public void setLastaccessip(String lastaccessip) {
        this.lastaccessip = lastaccessip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
