/*
 * Copyright (c) 2017. Nandan.
 */

package com.songning.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.songning.mvp.R;
import com.songning.mvp.base.BaseActivity;
import com.songning.mvp.mvp.login.ISplashInteracter;
import com.songning.mvp.mvp.login.SplashPresenter;

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
