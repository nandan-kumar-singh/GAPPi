/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.mvp.login;

import com.androidfluid.mvp.api.ApiEngine;
import com.androidfluid.mvp.model.Gank;
import com.androidfluid.mvp.rx.RxSchedulers;
import rx.Observable;

/**
 * Created by singh on 20/09/17.
 */

public class SplashModel implements ISplashInteracter.Model {

    @Override
    public Observable<Gank> authenticate() {
        return ApiEngine.getInstance().getApiService().getGank("1").compose(RxSchedulers.<Gank>switchThread());
    }
}
