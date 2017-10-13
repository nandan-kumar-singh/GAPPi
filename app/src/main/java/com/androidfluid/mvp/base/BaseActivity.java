/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Nandan on 2016/10/30.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (onCreatePresenter() != null) {
            mPresenter = onCreatePresenter();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }

    protected abstract P onCreatePresenter();
}
