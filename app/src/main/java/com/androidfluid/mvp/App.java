/*
 * Copyright (c) 2017. Nandan.
 */

package com.songning.mvp;

import android.app.Application;


public class App extends Application {

    private static App mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static App getContext() {
        return mContext;
    }
}
