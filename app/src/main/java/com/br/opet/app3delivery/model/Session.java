package com.br.opet.app3delivery.model;

import android.content.Context;

public class Session {

    private String email;
    private String password;

    private transient String token;
    private transient UserModel user;
    private transient Context mContext;

    public Session() {}

    public Session(UserModel user, String token) {
        this.user = user;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
