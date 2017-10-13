/*
 * Copyright (c) 2017. Nandan.
 */

package com.songning.mvp.mvp.main;

import com.songning.mvp.api.ApiEngine;
import com.songning.mvp.model.Gank;
import com.songning.mvp.rx.RxSchedulers;

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
