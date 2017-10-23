/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.mvp.main;

import android.content.Context;

import com.androidfluid.mvp.model.Contact;
import com.androidfluid.mvp.model.Gank;
import com.androidfluid.mvp.model.User;

import java.util.List;

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

    @Override
    public void showAppDescription(Context context) {
        Subscription subscribe = mModel.shouldShowAppDescription(context)
                .subscribe(new Subscriber<Boolean>() {
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
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean)
                            mView.goToAppDescription();
                    }
                });

        addSubscribe(subscribe);

    }

    @Override
    public void showFragment(User user) {

    }

    @Override
    public void getAllContact(Context context) {
        Subscription subscribe = mModel.getAllContact(context)
                .subscribe(new Subscriber<List<Contact>>() {
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
                    public void onNext(List<Contact> contactList) {
                        mView.updateContactList(contactList);
                    }

                });

        addSubscribe(subscribe);
    }
}
