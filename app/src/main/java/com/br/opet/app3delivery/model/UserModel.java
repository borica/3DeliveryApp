package com.br.opet.app3delivery.model;

import android.content.Context;

import com.br.opet.app3delivery.service.UserService;

public class UserModel {

    private String name;
    private String email;
    private String password;

    private transient Context mContext;

    public Boolean save() {
        UserService userService = new UserService();
        return userService.createNewUser(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
