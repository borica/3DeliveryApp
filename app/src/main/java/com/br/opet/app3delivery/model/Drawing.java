package com.br.opet.app3delivery.model;

import android.content.Context;

public class Drawing {

    private String id;
    private String name;
    private double height;
    private double width;

    private transient Context mContext;

    public Drawing(){}

    public Drawing(String id, String name, double height, double width, Context mContext){
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.height = height;
        this.mContext = mContext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
