/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.mvp.login;

import android.os.Handler;

/**
 * Created by singh on 20/09/17.
 */

public class SplashPresenter extends ISplashInteracter.Presenter {

    public SplashPresenter(ISplashInteracter.View view) {
        mView = view;
        mModel = new SplashModel();
    }

    @Override
    public void showSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.navigateToHomeScreen();
            }
        },2700);
    }

}
