package com.androidfluid.mvp.mvp.profile;

import com.androidfluid.mvp.base.BaseModel;
import com.androidfluid.mvp.base.BasePresenter;
import com.androidfluid.mvp.base.BaseView;

/**
 * Created by Nandan on 2016/10/30.
 */

public interface IProfileInteracter {

    interface View extends BaseView {

    }

    interface Model extends BaseModel {

    }

    abstract class Presenter extends BasePresenter<View, Model> {

    }
}
