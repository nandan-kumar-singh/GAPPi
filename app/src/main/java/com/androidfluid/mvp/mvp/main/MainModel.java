/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.mvp.main;

import com.androidfluid.mvp.api.ApiEngine;
import com.androidfluid.mvp.model.Gank;
import com.androidfluid.mvp.rx.RxSchedulers;

import rx.Observable;

/**
 * Created by Nandan on 2016/10/30.
 */

public class MainModel implements IMainInteracter.Model {

    @Override
    public Observable<Gank> getGank() {

        return ApiEngine.getInstance().getApiService()
                .getGank("1")
                .compose(RxSchedulers.<Gank>switchThread());
    }
}
