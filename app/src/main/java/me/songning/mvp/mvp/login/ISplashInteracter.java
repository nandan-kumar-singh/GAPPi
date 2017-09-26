/*
 * Copyright (c) 2017. Nandan.
 */

package me.songning.mvp.mvp.login;

import me.songning.mvp.base.BaseModel;
import me.songning.mvp.base.BasePresenter;
import me.songning.mvp.base.BaseView;
import me.songning.mvp.model.Gank;
import rx.Observable;

/**
 * Created by Nandan on 2016/10/30.
 */

public interface ISplashInteracter {

    interface View extends BaseView {
        void navigateToHomeScreen();
        void showProgress();
        void hideProgress();
        void onFail(String errorLog);
    }

    interface Model extends BaseModel {
        Observable<Gank> authenticate();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void showSplash();
    }
}
