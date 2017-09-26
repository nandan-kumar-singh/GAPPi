package me.songning.mvp.mvp.main;

import me.songning.mvp.base.BaseModel;
import me.songning.mvp.base.BasePresenter;
import me.songning.mvp.base.BaseView;
import me.songning.mvp.model.Gank;
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
