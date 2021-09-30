package com.example.jewelllerylogin;

import java.io.Serializable;

public class User implements Serializable {

    public String name, mail, phone, username;

    public User() {

    }

    public User(String name, String mail, String phone, String username) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }
}
