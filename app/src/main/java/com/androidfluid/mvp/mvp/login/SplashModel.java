/*
 * Copyright (c) 2017. Nandan.
 */

package com.songning.mvp.mvp.login;

import com.songning.mvp.api.ApiEngine;
import com.songning.mvp.model.Gank;
import com.songning.mvp.rx.RxSchedulers;
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
