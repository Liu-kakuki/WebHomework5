package com.example.demo.model;


import com.example.demo.data.LoginData;

public class CheckLogin {
    public static boolean Check(LoginData login) {
        if ("kakuki".equals(login.getUsername()) && "12345678".equals(login.getPassword()))
            return true;
        else
            return false;
    }
}