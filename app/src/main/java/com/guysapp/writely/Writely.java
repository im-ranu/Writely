package com.guysapp.writely;

import android.app.Application;

import shortbread.Shortbread;

public class Writely extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Shortbread.create(this);
    }
}
