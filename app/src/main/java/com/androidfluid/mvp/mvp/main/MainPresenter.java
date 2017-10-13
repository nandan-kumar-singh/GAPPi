/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.mvp.main;

import com.androidfluid.mvp.model.Gank;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Nandan on 2016/10/30.
 */

public class MainPresenter extends IMainInteracter.Presenter {

    public MainPresenter(IMainInteracter.View view) {
        mView = view;
        mModel = new MainModel();
    }

    @Override
    public void getGank() {
        Subscription subscribe = mModel.getGank()
                .subscribe(new Subscriber<Gank>() {
                    @Override
                    public void onStart() {
                        mView.showDialog();
                    }

                    @Override
                    public void onCompleted() {
                        mView.hideDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onFail(e.getMessage());
                        onCompleted();
                    }

                    @Override
                    public void onNext(Gank gank) {
                        mView.onSucceed(gank);
                    }
                });



        addSubscribe(subscribe);
    }
}
