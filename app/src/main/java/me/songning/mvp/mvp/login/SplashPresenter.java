/*
 * Copyright (c) 2017. Nandan.
 */

package me.songning.mvp.mvp.login;

import me.songning.mvp.model.Gank;
import rx.Subscriber;
import rx.Subscription;

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
        mView.showProgress();
        
        Subscription subscription = mModel.authenticate().subscribe(new Subscriber<Gank>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onFail(e.getMessage());
            }

            @Override
            public void onNext(Gank s) {
                mView.navigateToHomeScreen();
            }
        });

        addSubscribe(subscription);
    }
}
