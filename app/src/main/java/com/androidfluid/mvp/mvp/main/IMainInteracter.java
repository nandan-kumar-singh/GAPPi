/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.mvp.main;

import android.content.Context;

import com.androidfluid.mvp.base.BaseModel;
import com.androidfluid.mvp.base.BasePresenter;
import com.androidfluid.mvp.base.BaseView;
import com.androidfluid.mvp.model.Contact;
import com.androidfluid.mvp.model.Gank;
import com.androidfluid.mvp.model.User;

import java.util.List;

import rx.Observable;

/**
 * Created by Nandan on 2016/10/30.
 */

public interface IMainInteracter {

    interface View extends BaseView {

        void showDialog();

        void onSucceed(Gank data);

        void onFail(String err);

        void hideDialog();

        void goToAppDescription();

        void showProfileFragment(Contact contact);

        void updateContactList(List<Contact> user);
    }

    interface Model extends BaseModel {
        Observable<Gank> getGank();
        Observable<Boolean> shouldShowAppDescription(Context context);
        Observable<List<Contact>> getAllContact(Context context);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getGank();

        public abstract void showAppDescription(Context context);

        public abstract void showFragment(User user);
        public abstract void getAllContact(Context context);
    }
}
