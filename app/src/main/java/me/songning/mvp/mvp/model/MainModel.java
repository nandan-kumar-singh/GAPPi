package me.songning.mvp.mvp.model;

import me.songning.mvp.api.ApiEngine;
import me.songning.mvp.bean.Gank;
import me.songning.mvp.mvp.contract.MainContract;
import me.songning.mvp.rx.RxSchedulers;
import rx.Observable;

/**
 * Created by Nicholas on 2016/10/30.
 */

public class MainModel implements MainContract.Model {

    @Override
    public Observable<Gank> getGank() {
        return ApiEngine.getInstance().getApiService()
                .getGank("1")
                .compose(RxSchedulers.<Gank>switchThread());
    }
}
