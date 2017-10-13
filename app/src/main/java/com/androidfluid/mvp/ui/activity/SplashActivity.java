/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidfluid.mvp.R;
import com.androidfluid.mvp.base.BaseActivity;
import com.androidfluid.mvp.mvp.login.ISplashInteracter;
import com.androidfluid.mvp.mvp.login.SplashPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity<SplashPresenter> implements ISplashInteracter.View {

    private boolean isOpen;
    @BindView(R.id.ivLogo)
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mPresenter.showSplash();
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

}
