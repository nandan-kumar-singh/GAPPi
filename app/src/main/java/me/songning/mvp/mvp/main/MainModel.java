/*
 * Copyright (c) 2017. Nandan.
 */

package me.songning.mvp.mvp.main;

import me.songning.mvp.api.ApiEngine;
import me.songning.mvp.model.Gank;
import me.songning.mvp.rx.RxSchedulers;
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
