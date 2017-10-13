/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.mvp.main;

import com.androidfluid.mvp.base.BaseModel;
import com.androidfluid.mvp.base.BasePresenter;
import com.androidfluid.mvp.base.BaseView;
import com.androidfluid.mvp.model.Gank;
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

    }

    interface Model extends BaseModel {
        Observable<Gank> getGank();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getGank();
    }
}
