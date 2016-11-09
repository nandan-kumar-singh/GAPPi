package me.songning.mvp.mvp.presenter;

import me.songning.mvp.bean.Gank;
import me.songning.mvp.mvp.contract.MainContract;
import me.songning.mvp.mvp.model.MainModel;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Nicholas on 2016/10/30.
 */

public class MainPresenter extends MainContract.Presenter {

    public MainPresenter(MainContract.View view) {
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
}
