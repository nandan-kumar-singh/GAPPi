/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.androidfluid.mvp.R;
import com.androidfluid.mvp.base.BaseActivity;
import com.androidfluid.mvp.mvp.login.ISplashInteracter;
import com.androidfluid.mvp.mvp.login.SplashPresenter;
import com.androidfluid.mvp.ui.fragment.AboutAppFragment;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity<SplashPresenter> implements ISplashInteracter.View {

    private boolean isOpen;
    //@BindView(R.id.ivLogo)
    //View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mPresenter.showSplash();
        mPresenter.showAppDescription();

    }

    @Override
    protected SplashPresenter onCreatePresenter() {
        return new SplashPresenter(this);
    }


    @Override
    public void navigateToHomeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onFail(String errorLog) {

    }

    @Override
    public void goToAppDescription() {
        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        AboutAppFragment aboutAppFragment=AboutAppFragment.getInstance();
        aboutAppFragment.show(fragmentManager,"about-app");
        aboutAppFragment.setCancelable(true);
    }

}
