package com.example.instagrameclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("bIwe3RyFxOlCI6uxep60pPlrxqrPMY521sA1xSZ5")
                // if defined
                .clientKey("l9CRHHwsKRYa26E612iBX8nsDKIKKVyf8PkNdieW")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
