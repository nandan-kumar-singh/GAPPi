package com.androidfluid.mvp.mvp.profile;

/**
 * Created by Nandan on 2016/10/30.
 */

public class ProfilePresenter extends IProfileInteracter.Presenter {

    public ProfilePresenter(IProfileInteracter.View view) {
        mView = view;
        mModel = new ProfileModel();
    }


}
