/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidfluid.mvp.R;
import com.androidfluid.mvp.base.BaseActivity;
import com.androidfluid.mvp.model.Gank;
import com.androidfluid.mvp.mvp.main.IMainInteracter;
import com.androidfluid.mvp.mvp.main.MainPresenter;

import java.util.List;
import java.util.Random;


public class MainActivity extends BaseActivity<MainPresenter>
        implements IMainInteracter.View {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ProgressBar mDialog;
    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private TextView mTextView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mTextView = findViewById(R.id.tv);
        mFab = findViewById(R.id.fab);

        mDialog = new ProgressBar(this);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getGank();
            }
        });

        initViews();
    }

    private void initViews() {

        /*Observable.interval(new Random().nextInt(10), TimeUnit.SECONDS)
                .timeInterval()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TimeInterval<Long>>() {
                    @Override
                    public void call(TimeInterval<Long> longTimeInterval) {
                        Toast.makeText(MainActivity.this, "Time: " + longTimeInterval, Toast.LENGTH_SHORT).show();
                    }
                });*/

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView view = findViewById(R.id.navigation_view);
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Snackbar.make(mFab, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showDialog() {
        mDialog.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSucceed(Gank data) {

        Toast.makeText(this, "The request was successful", Toast.LENGTH_SHORT).show();
        List<Gank.Result> results = data.getResults();
        mTextView.setText(results.get(new Random().nextInt(10)).toString());

        for (Gank.Result result : results) {
            Log.e(TAG, result.toString());
        }
    }

    @Override
    public void onFail(String err) {
        Log.e(TAG, err);
        Toast.makeText(this, "Request failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideDialog() {
        mDialog.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
