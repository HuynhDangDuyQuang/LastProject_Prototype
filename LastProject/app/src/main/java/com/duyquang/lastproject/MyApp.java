package com.duyquang.lastproject;

import android.app.Application;

import io.realm.Realm;

public class MyApp extends Application {
    public static final int MAIN_ACTIVITY_REQUEST_CODE=1000;
    public static final int DASH_BOARD_REQUEST_CODE=1001;
    public static final int FOOD_DETAIL_ACTIVITY_RESULT_CODE=1002;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
