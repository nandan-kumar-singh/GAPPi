package me.songning.mvp;

import android.app.Application;

/**
 * Created by Nicholas on 2016/11/5.
 */

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
