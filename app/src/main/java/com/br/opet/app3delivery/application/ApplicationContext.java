package com.br.opet.app3delivery.application;

import android.app.Application;

import com.br.opet.app3delivery.model.Session;

public class ApplicationContext extends Application {

    private Session loggedUser;

    public Session getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Session loggedUser) {
        this.loggedUser = loggedUser;
    }
}
